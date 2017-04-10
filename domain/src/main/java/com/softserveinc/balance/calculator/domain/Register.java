package com.softserveinc.balance.calculator.domain;

public class Register {
    private Long id;
    private Long storeId;
    private String name;
    private String timezone;

    public static class Builder {
        private Long id;
        private Long storeId;
        private String name;
        private String timezone;
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setStoreId(Long storeId) {
            this.storeId = storeId;
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }
        
        public Register build() {
            Register register = new Register();
            register.setId(id);
            register.setStoreId(storeId);
            register.setName(name);
            register.setTimezone(timezone);
            return register;
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezome) {
        this.timezone = timezome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
        result = prime * result
                + ((timezone == null) ? 0 : timezone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Register other = (Register) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (storeId == null) {
            if (other.storeId != null)
                return false;
        } else if (!storeId.equals(other.storeId))
            return false;
        if (timezone == null) {
            if (other.timezone != null)
                return false;
        } else if (!timezone.equals(other.timezone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Register [id=" + id + ", storeId=" + storeId + ", name=" + name + ", timezone=" + timezone + "]";
    }
    
}
