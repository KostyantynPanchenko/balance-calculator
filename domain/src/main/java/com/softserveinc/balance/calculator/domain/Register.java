package com.softserveinc.balance.calculator.domain;

public class Register {

    private long id;
    private long storeId;
    private String name;
    private String timezome;
    
    public Register() { }

    public static class Builder {
        
        private Register register;
        
        public Builder() { }
        
        public Builder setId(Long id) {
            this.register.setId(id);
            return this;
        }
        
        public Builder setStoreId(Long storeId) {
            this.register.setStoreId(storeId);
            return this;
        }
        
        public Builder setName(String name) {
            this.register.setName(name);
            return this;
        }
        
        public Builder setTimezone(String timezone) {
            this.register.setTimezome(timezone);
            return this;
        }
        
        public Register build() {
            return this.register;
        }
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezome() {
        return timezome;
    }

    public void setTimezome(String timezome) {
        this.timezome = timezome;
    }
}
