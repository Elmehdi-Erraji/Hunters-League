package com.spring.huntersleague.domain;

import com.spring.huntersleague.domain.enums.Authority;
import com.spring.huntersleague.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "\"user\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min =8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit"
    )
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "CIN is required")
    private String cin;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "Join date is required")
    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

    @OneToMany(mappedBy = "user")
    private List<Participation> participations;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Assign role-based authorities
        authorities.add(new SimpleGrantedAuthority(role.name()));

        // Add specific authorities based on role
        switch (role) {
            case MEMBER:
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_PARTICIPATE.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_RANKINGS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_COMPETITIONS.name()));
                break;
            case JURY:
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_SCORE.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_PARTICIPATE.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_RANKINGS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_COMPETITIONS.name()));
                break;
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_MANAGE_COMPETITIONS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_MANAGE_USERS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_MANAGE_SPECIES.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_MANAGE_SETTINGS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_PARTICIPATE.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_RANKINGS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_VIEW_COMPETITIONS.name()));
                authorities.add(new SimpleGrantedAuthority(Authority.CAN_SCORE.name()));
                break;
        }

        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
