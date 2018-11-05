package fpt.oceanstudy.dto;

public class Fish {

    private Integer id;
    private String name;
    private Double weight;
    private Double length;
    private Double height;
    private Integer deep;
    private Integer age;
    private String img;
    private String video;
    private String status;
    /**
     * Get value of id.
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Get value of name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get value of weight.
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }
    /**
     * Set the value for weight.
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    /**
     * Get value of length.
     * @return the length
     */
    public Double getLength() {
        return length;
    }
    /**
     * Set the value for length.
     * @param length the length to set
     */
    public void setLength(Double length) {
        this.length = length;
    }
    /**
     * Get value of height.
     * @return the height
     */
    public Double getHeight() {
        return height;
    }
    /**
     * Set the value for height.
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }
    /**
     * Get value of deep.
     * @return the deep
     */
    public Integer getDeep() {
        return deep;
    }
    /**
     * Set the value for deep.
     * @param deep the deep to set
     */
    public void setDeep(Integer deep) {
        this.deep = deep;
    }
    /**
     * Get value of age.
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * Set the value for age.
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * Get value of img.
     * @return the img
     */
    public String getImg() {
        return img;
    }
    /**
     * Set the value for img.
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }
    /**
     * Get value of video.
     * @return the video
     */
    public String getVideo() {
        return video;
    }
    /**
     * Set the value for video.
     * @param video the video to set
     */
    public void setVideo(String video) {
        this.video = video;
    }
    /**
     * Get value of status.
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Set the value for status.
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    public Fish(Integer id, String name, Double weight, Double length, Double height, Integer deep, Integer age,
            String img, String video, String status) {
        super();
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.deep = deep;
        this.age = age;
        this.img = img;
        this.video = video;
        this.status = status;
    }
    public Fish() {
        super();
    }

}
