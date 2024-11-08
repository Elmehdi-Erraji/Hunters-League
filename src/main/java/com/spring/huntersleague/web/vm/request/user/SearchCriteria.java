package com.spring.huntersleague.web.vm.request.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SearchCriteria {
    private String username;
    private String cin;
}
