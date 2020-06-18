package com.tsilva.springFood.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Telmo Silva on 18.06.2020.
 */

public class AuthorityUtils
{
    public static List<String> getUserAuthorities(UserDetails userDetails)
    {
        if(!userDetails.getAuthorities().isEmpty())
        {
            List<SimpleGrantedAuthority> simpleGrantedAuthorityList =
                    userDetails.getAuthorities().stream().map(new Function<GrantedAuthority, SimpleGrantedAuthority>()
                    {
                        @Override
                        public SimpleGrantedAuthority apply(GrantedAuthority grantedAuthority)
                        {
                            return new SimpleGrantedAuthority(grantedAuthority.getAuthority());
                        }
                    }).collect(Collectors.toList());

            List<String> authorityList = new LinkedList<>();
            for(SimpleGrantedAuthority simpleGrantedAuthority: simpleGrantedAuthorityList)
            {
                authorityList.add(simpleGrantedAuthority.getAuthority());
            }

            return authorityList;
        }

        return null;
    }
}
