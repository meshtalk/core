package tech.lerk.meshtalk.entities;

public class MetaInfo implements Comparable<MetaInfo> {
    private int apiVersion;
    private String coreVersion;

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getCoreVersion() {
        return coreVersion;
    }

    public void setCoreVersion(String coreVersion) {
        this.coreVersion = coreVersion;
    }

    @Override
    public int compareTo(MetaInfo metaInfo) {
        return Integer.compare(apiVersion, metaInfo.apiVersion);
    }
}
