package com.ingenral.app.exceptions;

import com.ingenral.app.data.entities.BaseEntity;

public class ExceptionsUtility {

    public static void exceptionIfIdExistsForCreate(Object o) {
        if (o != null) {
            if (o instanceof BaseEntity) {
                Long id = ((BaseEntity) o).getId();
                if (id != null) {
                    throwCreateError();
                }
            } else {
                throwCreateError();
            }
        }
    }

    public static void exceptionIfIdDoesntExistForUpdate(Object o) {
        if (o == null){
            throwUpdateError();
        }
        if (o instanceof BaseEntity) {
            Long id = ((BaseEntity) o).getId();
            if (id == null) {
                throwUpdateError();
            }
        }
    }


    private static void throwCreateError() {
        throw new CustomException("Cannot create resource with identifier");
    }

    private static void throwUpdateError() {
        throw new CustomException("Cannot update resource without identifier");
    }
}
