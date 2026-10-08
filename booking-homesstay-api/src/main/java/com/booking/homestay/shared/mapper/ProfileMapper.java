package com.booking.homestay.shared.mapper;

import com.booking.homestay.model.User;
import com.booking.homestay.shared.dto.ProfileRequest;
import com.booking.homestay.shared.dto.ProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public abstract class ProfileMapper {
    @Mapping(target = "id",source = "user.id")
    @Mapping(target = "userName",source = "user.userName")
    @Mapping(target = "password",source = "user.password")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    @Mapping(target = "phone",source = "user.phone")
    @Mapping(target = "address",source = "user.address")
    @Mapping(target = "image",source = "user.image")
    @Mapping(target = "role",source = "user.role")
    @Mapping(target = "createdDate", source = "user.createdDate" )
    @Mapping(target = "dateOfBirth", expression = "java(getFormatDate(user))" )
    @Mapping(target = "sex", source = "user.sex" )
    @Mapping(target = "enabled", source = "user.enabled" )
    @Mapping(target = "status", source = "user.status" )
    @Mapping(target = "id_creator", source = "creator.id" )
    @Mapping(target = "id_homeStay", source = "user.homeStay.id")
    @Mapping(target = "homeStayName", source = "user.homeStay.homeStayName")
    public abstract ProfileResponse mapToDtoByUserName(User user);

    String getFormatDate(User user){
        if(user.getDateOfBirth() != null){
            DateFormat df3= new SimpleDateFormat("yyyy-MM-dd");
            return df3.format(user.getDateOfBirth());

        }else{
            return "";
        }
    }

    @Mapping(target = "id",source = "user.id")
    @Mapping(target = "userName",source = "user.userName")
    @Mapping(target = "password",source = "user.password")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    @Mapping(target = "phone",source = "user.phone")
    @Mapping(target = "address",source = "user.address")
    @Mapping(target = "image",source = "user.image")
    @Mapping(target = "role",source = "user.role")
    @Mapping(target = "createdDate", source = "user.createdDate" )
    @Mapping(target = "dateOfBirth", expression = "java(getFormatDate(user))" )
    @Mapping(target = "sex", source = "user.sex" )
    @Mapping(target = "enabled", source = "user.enabled" )
    @Mapping(target = "status", source = "user.status" )
    @Mapping(target = "id_creator", source = "creator.id" )
    @Mapping(target = "id_homeStay", source = "user.homeStay.id")
    @Mapping(target = "homeStayName", source = "user.homeStay.homeStayName")
    public abstract User mapEditToDtoById(ProfileRequest profileRequest,User user);

    @Mapping(target = "id",source = "user.id")
    @Mapping(target = "userName",source = "user.userName")
    @Mapping(target = "password",source = "user.password")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    @Mapping(target = "phone",source = "user.phone")
    @Mapping(target = "address",source = "user.address")
    @Mapping(target = "image",source = "user.image")
    @Mapping(target = "role",source = "user.role")
    @Mapping(target = "createdDate", source = "user.createdDate" )
    @Mapping(target = "dateOfBirth", expression = "java(getFormatDate(user))" )
    @Mapping(target = "sex", source = "user.sex" )
    @Mapping(target = "enabled", source = "user.enabled" )
    @Mapping(target = "status", source = "user.status" )
    @Mapping(target = "id_creator", source = "creator.id" )
    @Mapping(target = "id_homeStay", source = "user.homeStay.id")
    @Mapping(target = "homeStayName", source = "user.homeStay.homeStayName")
    public abstract User mapUpdateToDtoById(ProfileRequest profileRequest,User user);

}
