package xyz.housie.app.securityjwtservice.role.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.housie.app.securityjwtservice.role.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {
    public Role findRoleByRole(String role);
}
