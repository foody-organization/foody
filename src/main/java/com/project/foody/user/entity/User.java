package com.project.foody.user.entity;

import com.project.foody.base.entity.BaseEntity;
import com.project.foody.user.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update user set deleted = true where id = ?")
public class User extends BaseEntity {

//    @Column(nullable = false, unique = true, length = 50)
//    private String username; // 로그인 ID 또는 식별용 사용자명

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Column(nullable = false, unique = true, length = 100)
    private String email; // 이메일 로그인시 사용

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Column(nullable = false)
    private String password; // 비밀번호 (BCrypt 등으로 암호화 저장)

    @Column(nullable = false, length = 100)
    private String name; // 사용자 이름

    @Column(length = 20)
    private String phone; // 전화번호 (선택 입력 가능)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 사용자 역할 (이용자 / 식당사장 / 관리자)

//    // 전체 주소 (예: 서울특별시 강남구 테헤란로 123)
//    @Column(length = 255, nullable = false)
//    private String address;

    // 주소 필드 추가
    @Column(length = 20, nullable = false)
    private String sido; // 시/도 (예: 서울특별시)

    @Column(length = 20, nullable = false)
    private String sigungu; // 시/군/구 (예: 강남구)

    @Column(length = 50, nullable = false)
    private String roadAddress; // 도로명 주소 (예: 테헤란로 123)

    @Column(length = 10, nullable = false)
    private String zipCode; // 우편번호

    // 큰 단위만 수집하여 집 근처 레스토랑을 추천하기 위해 주소가 있는 것이기 때문에 상세 주소 필드는 필요없다고 생각해서 생략

    /**
     * 시도, 시군구, 도로명 주소를 조합한 전체 주소
     */
    public String getFullAddress() {
        return String.format("%s %s %s", sido, sigungu, roadAddress != null ? roadAddress : "");
    }

    // 위도, 경도 좌표 (지도 API사용)
    private Double latitude;  // 위도
    private Double longitude; // 경도




}
