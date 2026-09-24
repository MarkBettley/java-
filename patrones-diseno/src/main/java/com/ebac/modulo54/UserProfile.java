package com.ebac.modulo54;

public class UserProfile {

    private final String name;
    private final String email;
    private final int age;
    private final String city;

    private UserProfile(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.city = builder.city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private String city;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
