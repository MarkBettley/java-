package com.ebac.modulo54;

public class BuilderContext {

    public UserProfile createUserProfile() {
        return new UserProfile.Builder()
                .name("Marco")
                .email("marco@email.com")
                .age(31)
                .city("Pachuca")
                .build();
    }

    public void showUserProfile() {
        UserProfile profile = createUserProfile();
        System.out.println(profile);
    }
}
