package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "number",
        "size",
        "total_pages",
        "total_elements",
        "first",
        "number_of_elements",
        "last",
        "content"
})
public class PageOfUserDTO {

    private long number;
    private long size;
    @JsonProperty(namespace = "total_pages")
    private long totalPages;
    @JsonProperty(namespace = "total_elements")
    private long totalElements;
    private boolean first;
    @JsonProperty(namespace = "number_of_elements")
    private long numberOfElements;
    private boolean last;
    private UserDTO content;

    public PageOfUserDTO() {
    }

    public PageOfUserDTO(long number, long size, long totalPages,
                         long totalElements, boolean first,
                         long numberOfElements, boolean last,
                         UserDTO content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public UserDTO getContent() {
        return content;
    }

    public void setContent(UserDTO content) {
        this.content = content;
    }
}
