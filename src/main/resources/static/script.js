// Kakao SDK는 HTML에서 autoload=false로 먼저 로드하세요.
// <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=...&libraries=services&autoload=false"></script>
// <script src="/script.js"></script>

window.onload = function () {
    kakao.maps.load(function () {
        // 1) 지도 생성
        const mapContainer = document.getElementById('map');
        const mapOption = {
            center: new kakao.maps.LatLng(37.566826, 126.9786567),
            level: 3
        };
        const map = new kakao.maps.Map(mapContainer, mapOption);

        // 2) 장소검색/인포윈도우/마커 배열
        const ps = new kakao.maps.services.Places();
        const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
        let markers = [];

        // 3) 검색 인풋: 클릭(포커스)하면 한 번만 전체 비우기
        const keywordInput = document.getElementById('keyword');
        if (keywordInput) {
            keywordInput.addEventListener('focus', function () {
                if (this.dataset.cleared !== '1') {
                    this.value = '';
                    this.dataset.cleared = '1';
                }
            });
        }

        // 전역에서 폼이 호출할 수 있도록 검색 함수 노출
        window.searchPlaces = function () {
            const keyword = (document.getElementById('keyword').value || '').trim();
            if (!keyword) {
                alert('키워드를 입력해주세요!');
                return;
            }
            ps.keywordSearch(keyword, placesSearchCB);
        };

        // 검색 콜백
        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
                displayPlaces(data);
                displayPagination(pagination);
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                clearListAndMarkers();
                alert('검색 결과가 없습니다.');
            } else {
                clearListAndMarkers();
                alert('검색 중 오류가 발생했습니다.');
            }
        }

        // 목록 + 마커 표시
        function displayPlaces(places) {
            const listEl = document.getElementById('placesList');
            const menuEl = document.getElementById('menu_wrap');
            const fragment = document.createDocumentFragment();
            const bounds = new kakao.maps.LatLngBounds();

            removeAllChildNodes(listEl);
            removeMarkers();

            places.forEach((place, i) => {
                const position = new kakao.maps.LatLng(place.y, place.x);
                const marker = addMarker(position, i);
                const itemEl = getListItem(i, place);

                bounds.extend(position);

                // 마커 hover
                kakao.maps.event.addListener(marker, 'mouseover', () =>
                    displayInfowindow(marker, place.place_name)
                );
                kakao.maps.event.addListener(marker, 'mouseout', () => infowindow.close());

                // 마커/리스트 클릭 → 상세 + 리뷰 패널
                kakao.maps.event.addListener(marker, 'click', () => {
                    displayPlaceDetail(place);
                    openReviews(place); // 리뷰 패널 열기
                });
                itemEl.onclick = () => {
                    displayPlaceDetail(place);
                    openReviews(place);
                };

                // 리스트 hover 시 마커 인포
                itemEl.onmouseover = () => displayInfowindow(marker, place.place_name);
                itemEl.onmouseout = () => infowindow.close();

                fragment.appendChild(itemEl);
            });

            listEl.appendChild(fragment);
            if (!bounds.isEmpty()) map.setBounds(bounds);
            menuEl.scrollTop = 0;
        }

        // 리스트 아이템 DOM
        function getListItem(index, place) {
            const el = document.createElement('li');
            el.className = 'item';
            el.innerHTML = `
        <span class="markerbg marker_${index + 1}"></span>
        <div class="info">
          <h5>${place.place_name || ''}</h5>
          <span>${place.road_address_name || place.address_name || ''}</span>
          <span class="jibun gray">${place.road_address_name ? place.address_name : ''}</span>
          <span class="tel">${place.phone || ''}</span>
        </div>
      `;
            return el;
        }

        // 마커 생성
        function addMarker(position, idx) {
            const imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png';
            const imageSize = new kakao.maps.Size(36, 37);
            const imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691),
                spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10),
                offset: new kakao.maps.Point(13, 37)
            };
            const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
            const marker = new kakao.maps.Marker({ position, image: markerImage });
            marker.setMap(map);
            markers.push(marker);
            return marker;
        }

        function removeMarkers() {
            markers.forEach(m => m.setMap(null));
            markers = [];
        }

        function displayPagination(pagination) {
            const paginationEl = document.getElementById('pagination');
            while (paginationEl.hasChildNodes()) paginationEl.removeChild(paginationEl.lastChild);

            for (let i = 1; i <= pagination.last; i++) {
                const a = document.createElement('a');
                a.href = '#';
                a.innerHTML = i;
                if (i === pagination.current) {
                    a.className = 'on';
                } else {
                    a.onclick = (e) => {
                        e.preventDefault();
                        pagination.gotoPage(i);
                    };
                }
                paginationEl.appendChild(a);
            }
        }

        function displayInfowindow(marker, title) {
            infowindow.setContent(`<div style="padding:5px;z-index:1;">${title}</div>`);
            infowindow.open(map, marker);
        }

        function removeAllChildNodes(el) {
            while (el.firstChild) el.removeChild(el.firstChild);
        }

        function clearListAndMarkers() {
            removeAllChildNodes(document.getElementById('placesList'));
            removeMarkers();
            document.getElementById('pagination').innerHTML = '';
            closeReviewPanel(); // 검색할 때 리뷰 패널 닫기
        }

        // 현재 위치 기반 초기 검색
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const lat = position.coords.latitude;
                    const lng = position.coords.longitude;
                    const userPosition = new kakao.maps.LatLng(lat, lng);
                    map.setCenter(userPosition);

                    const geocoder = new kakao.maps.services.Geocoder();
                    geocoder.coord2RegionCode(lng, lat, function (result, status) {
                        let keyword = '맛집';
                        if (status === kakao.maps.services.Status.OK && result.length > 0) {
                            const region =
                                result[0].region_3depth_name ||
                                result[0].region_2depth_name ||
                                '내 위치';
                            keyword = `${region} 맛집`;
                        }
                        const input = document.getElementById('keyword');
                        if (input) input.value = keyword;
                        searchPlaces();
                    });
                },
                () => {
                    alert('위치 권한이 거부되어 기본 키워드로 검색합니다.');
                    searchPlaces();
                }
            );
        } else {
            alert('이 브라우저는 위치 서비스를 지원하지 않습니다.');
            searchPlaces();
        }

        // 상세 패널
        function displayPlaceDetail(place) {
            const detailDiv = document.getElementById('test-div');
            if (!detailDiv) return;
            detailDiv.innerHTML = `
        <h3>${place.place_name}</h3>
        <p><strong>주소:</strong> ${place.road_address_name || place.address_name || ''}</p>
        <p><strong>전화번호:</strong> ${place.phone || '없음'}</p>
        <p><a href="${place.place_url}" target="_blank" rel="noopener">카카오 상세보기 ↗</a></p>
        <p>그 밖의 json값</p>
        <pre style="white-space:pre-wrap; word-break:break-all;">${JSON.stringify(place, null, 2)}</pre>
      `;
        }

        // ─────────────────────────────────────────────
        // 리뷰: 패널 열기 + 목록 조회 + 등록
        // ─────────────────────────────────────────────
        window.openReviews = function (place) {
            document.getElementById('review-place-title').textContent =
                `리뷰 — ${place.place_name}`;
            document.getElementById('review-placeId').value = place.id;
            document.getElementById('review-placeName').value = place.place_name || '';
            document.getElementById('review-address').value =
                place.road_address_name || place.address_name || '';

            loadReviews(place.id);

            const panel = document.getElementById('review-panel');
            panel.style.display = 'block';
            panel.scrollIntoView({ behavior: 'smooth', block: 'start' });

            document.getElementById('review-nickname').value ||= '';
            document.getElementById('review-rating').value = '5';
            document.getElementById('review-content').value = '';
        };

        window.closeReviewPanel = function () {
            document.getElementById('review-panel').style.display = 'none';
        };

        async function loadReviews(placeId) {
            const ul = document.getElementById('review-list');
            ul.innerHTML = '<li>불러오는 중…</li>';

            try {
                const res = await fetch(`/api/reviews?placeId=${encodeURIComponent(placeId)}`);
                if (!res.ok) throw new Error(`HTTP ${res.status}`);
                const items = await res.json();

                if (!items.length) {
                    ul.innerHTML = '<li>아직 리뷰가 없습니다. 첫 리뷰를 작성해보세요!</li>';
                    return;
                }

                ul.innerHTML = '';
                items.forEach(r => {
                    const li = document.createElement('li');
                    li.style.marginBottom = '10px';
                    const created = (r.createDate || r.createdAt || '').toString().replace('T',' ');
                    li.innerHTML = `
                        <div><strong>${escapeHtml(r.nickname)}</strong> · ⭐ ${r.rating}</div>
                        <div style="white-space:pre-wrap;">${escapeHtml(r.content || '')}</div>
                        <div style="color:#888;font-size:12px;">${created}</div>
                        <hr>
                    `;
                    ul.appendChild(li);
                });
            } catch (e) {
                ul.innerHTML = `<li>리뷰를 불러오지 못했습니다: ${e.message}</li>`;
            }
        }

        window.submitReview = async function () {
            const payload = {
                placeId:   document.getElementById('review-placeId').value,
                placeName: document.getElementById('review-placeName').value,
                address:   document.getElementById('review-address').value,
                nickname:  document.getElementById('review-nickname').value.trim(),
                rating:    Number(document.getElementById('review-rating').value),
                content:   document.getElementById('review-content').value.trim()
            };

            if (!payload.placeId || !payload.nickname || !payload.rating || !payload.content) {
                alert('필수 항목을 채워주세요.');
                return false;
            }

            try {
                const res = await fetch('/api/reviews', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });
                if (!res.ok) throw new Error(`HTTP ${res.status}`);

                document.getElementById('review-content').value = '';
                loadReviews(payload.placeId);
            } catch (e) {
                alert('리뷰 등록 실패: ' + e.message);
            }
            return false;
        };

        function escapeHtml(s) {
            return (s || '')
                .replaceAll('&','&amp;')
                .replaceAll('<','&lt;')
                .replaceAll('>','&gt;')
                .replaceAll('"','&quot;')
                .replaceAll("'",'&#39;');
        }
    });
};
