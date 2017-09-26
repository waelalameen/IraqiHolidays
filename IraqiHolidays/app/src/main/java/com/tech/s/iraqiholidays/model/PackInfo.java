package com.tech.s.iraqiholidays.model;

import com.google.gson.annotations.SerializedName;

public class PackInfo {
    @SerializedName("pid")
    private String pId;
    @SerializedName("pdetail")
    private String pDetail;
    @SerializedName("dateadded")
    private String dateAdded;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("pfrom")
    private String pFrom;
    @SerializedName("pto")
    private String pTo;
    @SerializedName("from_country")
    private String fromCountry;
    @SerializedName("to_country")
    private String toCountry;
    @SerializedName("sid")
    private String sid;
    @SerializedName("lastdateap")
    private String lastDateAp;
    @SerializedName("pcurrency")
    private String pCurrency;
    @SerializedName("pactive")
    private String pActive;
    @SerializedName("package_type")
    private String packageType;
    @SerializedName("cxl_deadline")
    private String cxlDeadline;
    @SerializedName("include")
    private String include;
    @SerializedName("not_include")
    private String notInclude;
    @SerializedName("requirements")
    private String requirements;
    @SerializedName("fw")
    private String fw;
    @SerializedName("pprice")
    private String pPrice;
    @SerializedName("to_name")
    private String toName;
    @SerializedName("image")
    private String image;
    @SerializedName("supplier_name")
    private String supplierName;

    public PackInfo(String pId, String pDetail, String dateAdded, String startDate, String endDate, String pFrom, String pTo, String fromCountry, String toCountry, String sid, String lastDateAp, String pCurrency, String pActive, String packageType, String cxlDeadline, String include, String notInclude, String requirements, String fw, String pPrice, String toName, String image, String supplierName) {
        this.pId = pId;
        this.pDetail = pDetail;
        this.dateAdded = dateAdded;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pFrom = pFrom;
        this.pTo = pTo;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.sid = sid;
        this.lastDateAp = lastDateAp;
        this.pCurrency = pCurrency;
        this.pActive = pActive;
        this.packageType = packageType;
        this.cxlDeadline = cxlDeadline;
        this.include = include;
        this.notInclude = notInclude;
        this.requirements = requirements;
        this.fw = fw;
        this.pPrice = pPrice;
        this.toName = toName;
        this.image = image;
        this.supplierName = supplierName;
    }

    public String getpId() {
        return pId;
    }

    public String getpDetail() {
        return pDetail;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getpFrom() {
        return pFrom;
    }

    public String getpTo() {
        return pTo;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public String getToCountry() {
        return toCountry;
    }

    public String getSid() {
        return sid;
    }

    public String getLastDateAp() {
        return lastDateAp;
    }

    public String getpCurrency() {
        return pCurrency;
    }

    public String getpActive() {
        return pActive;
    }

    public String getPackageType() {
        return packageType;
    }

    public String getCxlDeadline() {
        return cxlDeadline;
    }

    public String getInclude() {
        return include;
    }

    public String getNotInclude() {
        return notInclude;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getFw() {
        return fw;
    }

    public String getpPrice() {
        return pPrice;
    }

    public String getToName() {
        return toName;
    }

    public String getImage() {
        return image;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
