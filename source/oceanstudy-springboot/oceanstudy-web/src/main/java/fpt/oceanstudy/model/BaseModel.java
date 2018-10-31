/**
 * Copyright 2018, MKS GROUP.
 */
package fpt.oceanstudy.model;

import java.util.List;

/**
 * @author ThachLN
 */
public class BaseModel {
    private List header;
    private List data;
    
    /** Store id of addresses marked deleting. */
    private List<Integer> deletedIds;
    /**
    * Get value of header.
    * @return the header
    */
    public List getHeader() {
        return header;
    }
    /**
    * Get value of data.
    * @return the data
    */
    public List getData() {
        return data;
    }
    /**
     * Set the value for header.
     * @param header the header to set
     */
    public void setHeader(List header) {
        this.header = header;
    }
    /**
     * Set the value for data.
     * @param data the data to set
     */
    public void setData(List data) {
        this.data = data;
    }

    /**
    * Get value of deletedIds.
    * @return the deletedIds
    */
    public List<Integer> getDeletedIds() {
        return deletedIds;
    }
    /**
     * Set the value for deletedIds.
     * @param deletedIds the deletedIds to set
     */
    public void setDeletedIds(List<Integer> deletedIds) {
        this.deletedIds = deletedIds;
    }
    /**
     * [Explain the description for this method here].
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("ReportModel [header=%s, data=%s]", header, data);
    }
    
}
