package core.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

@JsonPropertyOrder({
        "pageEssence",
        "content"
})
public class PageOfUserDTO {

    @JsonUnwrapped
    private PageEssence pageEssence;
    private List<UserDTO> content;

    public PageOfUserDTO() {
    }

    public PageOfUserDTO(PageEssence pageEssence,
                         List<UserDTO> content) {
        this.pageEssence = pageEssence;
        this.content = content;
    }

    public List<UserDTO> getContent() {
        return content;
    }
    public void setContent(List<UserDTO> content) {
        this.content = content;
    }

    public PageEssence getPageEssence() {
        return pageEssence;
    }

    public void setPageEssence(PageEssence pageEssence) {
        this.pageEssence = pageEssence;
    }
}
