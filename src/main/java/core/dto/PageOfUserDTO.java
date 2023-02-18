package core.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import dao.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    public PageOfUserDTO (Page<User> userPage) {
        this.pageEssence = new PageEssence();
        this.pageEssence.setFirst(userPage.isFirst());
        this.pageEssence.setLast(userPage.isLast());
        this.pageEssence.setTotalPages(userPage.getTotalPages());
        this.pageEssence.setNumber(userPage.getNumber());
        this.pageEssence.setNumberOfElements(userPage.getNumberOfElements());
        this.pageEssence.setTotalElements(userPage.getTotalElements());

        this.content = userPage
                .getContent()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
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
