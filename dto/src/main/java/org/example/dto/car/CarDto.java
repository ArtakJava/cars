package org.example.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private long id;
    @Pattern(regexp = "^\\p{L}*$")
    private String driverName;
    @Pattern(regexp = "^\\p{L}*$")
    private String brand;
    @NotNull
    private Long garageId;
}