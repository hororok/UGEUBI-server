package duksung.backend.hororok.ugeubi.medicine.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MedicineType {
    PILL("알약"),
    LIQUID("물약"),
    POWDER("가루약"),
    CREAM("연고"),
    PRESCRIPTION("처방약");

    private final String typeDescription;
}
