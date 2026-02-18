package com.emerald.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.emerald.exception.DepartmentNotFoundException;
import com.emerald.exception.LocationNotFoundException;
import com.emerald.model.Departments;
import com.emerald.model.Employee;
import com.emerald.model.Location;
import com.emerald.repository.DepartmentRepository;
import com.emerald.repository.LocationRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class Tokenizer {
    private final LocationRepository locationRepository;
    private final DepartmentRepository departmentRepository;

    // --- Constructor Injection ---
    /**
     * Constructor used by Spring to inject the required repository dependencies.
     */
    public Tokenizer(
        LocationRepository locationRepository,
        DepartmentRepository departmentRepository
        ) {
        this.locationRepository = locationRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createToken(Employee employee){

        final String secretString = "RockertSoftwareRocks2025ThisIsNotSecureEnough";
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

        Departments department = departmentRepository.findById(employee.getDepartment())
            .orElseThrow(() -> new DepartmentNotFoundException(employee.getDepartment()));
        Location location = locationRepository.findById(employee.getLocation())
            .orElseThrow(() -> new LocationNotFoundException(employee.getLocation()));

        

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + TimeUnit.MINUTES.toMillis(60));

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", employee.getId());
        claims.put("first_name", employee.getFirstName());
        claims.put("last_name", employee.getLastName());
        claims.put("location", location.getCountry());     //fix
        claims.put("department", department.getName()); ///////////fix
        claims.put("title", employee.getTitle());

        String builder =  Jwts.builder()
                .issuer("Auth Service")
                .claims(claims)
                .subject((employee.getFirstName() + " " + employee.getLastName()))
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();

        return builder;
    }
}
