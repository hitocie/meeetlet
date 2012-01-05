package com.meeetlet.meta.common;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-01-05 23:31:32")
/** */
public final class CityMeta extends org.slim3.datastore.ModelMeta<com.meeetlet.model.common.City> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City> name = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City>(this, "name", "name");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City> prefecture = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City>(this, "prefecture", "prefecture");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, java.util.Date> timestamp = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, java.util.Date>(this, "timestamp", "timestamp", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.common.City, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City> yomi = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.common.City>(this, "yomi", "yomi");

    private static final CityMeta slim3_singleton = new CityMeta();

    /**
     * @return the singleton
     */
    public static CityMeta get() {
       return slim3_singleton;
    }

    /** */
    public CityMeta() {
        super("City", com.meeetlet.model.common.City.class);
    }

    @Override
    public com.meeetlet.model.common.City entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.meeetlet.model.common.City model = new com.meeetlet.model.common.City();
        model.setKey(entity.getKey());
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setPrefecture((java.lang.String) entity.getProperty("prefecture"));
        model.setTimestamp((java.util.Date) entity.getProperty("timestamp"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setYomi((java.lang.String) entity.getProperty("yomi"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("name", m.getName());
        entity.setProperty("prefecture", m.getPrefecture());
        entity.setProperty("timestamp", m.getTimestamp());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("yomi", m.getYomi());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.meeetlet.model.common.City m = (com.meeetlet.model.common.City) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getPrefecture() != null){
            writer.setNextPropertyName("prefecture");
            encoder0.encode(writer, m.getPrefecture());
        }
        if(m.getTimestamp() != null){
            writer.setNextPropertyName("timestamp");
            encoder0.encode(writer, m.getTimestamp());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        if(m.getYomi() != null){
            writer.setNextPropertyName("yomi");
            encoder0.encode(writer, m.getYomi());
        }
        writer.endObject();
    }

    @Override
    protected com.meeetlet.model.common.City jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.meeetlet.model.common.City m = new com.meeetlet.model.common.City();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("prefecture");
        m.setPrefecture(decoder0.decode(reader, m.getPrefecture()));
        reader = rootReader.newObjectReader("timestamp");
        m.setTimestamp(decoder0.decode(reader, m.getTimestamp()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("yomi");
        m.setYomi(decoder0.decode(reader, m.getYomi()));
        return m;
    }
}