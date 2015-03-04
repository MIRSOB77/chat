package chat.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Permission;
import java.util.Set;

/**
 * Created by mirsad on 03.03.15.
 */
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private Long roleId;

    private String roleName;

    private Set<ChatPermission> permissions;
}
