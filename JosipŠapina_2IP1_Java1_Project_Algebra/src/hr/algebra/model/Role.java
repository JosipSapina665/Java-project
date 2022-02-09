/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.Optional;

/**
 *
 * @author josip
 */
public enum Role {
    ADMIN(1),
    USER(2);

    private final int role;

    private Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public static Optional<Role> from(int role) {
        Role found = null;
        for (Role value : values()) {
            if (value.role == role) {
                found = value;
            }
        }
        return Optional.ofNullable(found);
    }

}
