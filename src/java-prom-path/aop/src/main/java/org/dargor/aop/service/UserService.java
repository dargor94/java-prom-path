package org.dargor.aop.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    public List<Integer> getUsersIds(Integer q) {
        if (q <= 0) throw new RuntimeException("Error");
        return Stream.of(1, 2, 3, 4, 5).limit(q).collect(Collectors.toList());
    }

}
