package com.zenve.store;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;

/**
 * @description:
 * @className: User
 * @author: liwen
 * @date: 2020/8/2 10:17
 */

public class ApplicatonStore {

    public static String ICON_FONT_KEY = "icon.svg";
    private static SimpleBooleanProperty style = new SimpleBooleanProperty();
    private static SimpleIntegerProperty status = new SimpleIntegerProperty();
    private static SimpleStringProperty code = new SimpleStringProperty();
    private static SimpleStringProperty token = new SimpleStringProperty();
    private static SimpleStringProperty name = new SimpleStringProperty();
    private static SimpleStringProperty avatar = new SimpleStringProperty();
    private static SimpleStringProperty introduction = new SimpleStringProperty();
    private static MapProperty<String, String> featureMap;


    public static int getStatus() {
        return status.get();
    }

    public static SimpleIntegerProperty statusProperty() {
        return status;
    }

    public static void setStatus(int status) {
        ApplicatonStore.status.set(status);
    }

    public static String getCode() {
        return code.get();
    }

    public static SimpleStringProperty codeProperty() {
        return code;
    }

    public static void setCode(String code) {
        ApplicatonStore.code.set(code);
    }

    public static String getToken() {
        return token.get();
    }

    public static SimpleStringProperty tokenProperty() {
        return token;
    }

    public static void setToken(String token) {
        ApplicatonStore.token.set(token);
    }

    public static String getName() {
        return name.get();
    }

    public static SimpleStringProperty nameProperty() {
        return name;
    }

    public static void setName(String name) {
        ApplicatonStore.name.set(name);
    }

    public static String getAvatar() {
        return avatar.get();
    }

    public static SimpleStringProperty avatarProperty() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        ApplicatonStore.avatar.set(avatar);
    }

    public static String getIntroduction() {
        return introduction.get();
    }

    public static SimpleStringProperty introductionProperty() {
        return introduction;
    }

    public static void setIntroduction(String introduction) {
        ApplicatonStore.introduction.set(introduction);
    }


    public static MapProperty<String, String> getFeatureMap() {
        if (featureMap == null) {
            ObservableMap<String, String> map = FXCollections.observableMap(new HashMap<>());
            featureMap = new SimpleMapProperty<>(map);
        }
        return featureMap;
    }

    public static boolean isStyle() {
        return style.get();
    }

    public static SimpleBooleanProperty styleProperty() {
        return style;
    }

    public static void setStyle(boolean style) {
        ApplicatonStore.style.set(style);
    }

    public static void clearPermissionInfo() {
        setName("");
        getFeatureMap().clear();
    }


}


