package tandraym.edu.rpg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Description {
    private String value;
    private String chat;
    @JsonProperty("short")
    private String shortDescription;
}
