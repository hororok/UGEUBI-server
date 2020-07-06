package duksung.backend.hororok.ugeubi.notification.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    VALID_TERM("유통기한 알람"),
    TAKING_TIME("복용시간 알람");

    private final String typeDescription;
}
