package com.site.user;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Дмитрий on 24.09.2017.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByLogin(String login);
}
