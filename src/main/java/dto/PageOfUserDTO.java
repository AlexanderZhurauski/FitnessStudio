package dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonPropertyOrder({
        "pageEssence",
        "content"
})
public class PageOfUserDTO {

    @JsonUnwrapped
    private PageEssence pageEssence;
    private UserDTO content;

    public PageOfUserDTO() {
    }

    public PageOfUserDTO(PageEssence pageEssence,
                         UserDTO content) {
        this.pageEssence = pageEssence;
        this.content = content;
    }

    public UserDTO getContent() {
        return content;
    }
    public void setContent(UserDTO content) {
        this.content = content;
    }

    public PageEssence getPageEssence() {
        return pageEssence;
    }

    public void setPageEssence(PageEssence pageEssence) {
        this.pageEssence = pageEssence;
    }
}
