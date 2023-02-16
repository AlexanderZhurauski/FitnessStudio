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
    private long totalPages;
    private long totalElements;
    private boolean first;
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
    @JsonProperty("total_pages")
    public long getTotalPages() {
        return totalPages;
    }
    @JsonProperty("total_pages")
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
    @JsonProperty("total_elements")
    public long getTotalElements() {
        return totalElements;
    }
    @JsonProperty("total_elements")
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    public boolean isFirst() {
        return first;
    }
    public void setFirst(boolean first) {
        this.first = first;
    }
    @JsonProperty("number_of_elements")
    public long getNumberOfElements() {
        return numberOfElements;
    }
    @JsonProperty("number_of_elements")
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
