package com.bisheng.services.exhibit.model.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WordInExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public WordInExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andInIdIsNull() {
            addCriterion("in_id is null");
            return (Criteria) this;
        }

        public Criteria andInIdIsNotNull() {
            addCriterion("in_id is not null");
            return (Criteria) this;
        }

        public Criteria andInIdEqualTo(Long value) {
            addCriterion("in_id =", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotEqualTo(Long value) {
            addCriterion("in_id <>", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThan(Long value) {
            addCriterion("in_id >", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThanOrEqualTo(Long value) {
            addCriterion("in_id >=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThan(Long value) {
            addCriterion("in_id <", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThanOrEqualTo(Long value) {
            addCriterion("in_id <=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdIn(List<Long> values) {
            addCriterion("in_id in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotIn(List<Long> values) {
            addCriterion("in_id not in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdBetween(Long value1, Long value2) {
            addCriterion("in_id between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotBetween(Long value1, Long value2) {
            addCriterion("in_id not between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdIsNull() {
            addCriterion("exhibit_id is null");
            return (Criteria) this;
        }

        public Criteria andExhibitIdIsNotNull() {
            addCriterion("exhibit_id is not null");
            return (Criteria) this;
        }

        public Criteria andExhibitIdEqualTo(Long value) {
            addCriterion("exhibit_id =", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdNotEqualTo(Long value) {
            addCriterion("exhibit_id <>", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdGreaterThan(Long value) {
            addCriterion("exhibit_id >", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exhibit_id >=", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdLessThan(Long value) {
            addCriterion("exhibit_id <", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdLessThanOrEqualTo(Long value) {
            addCriterion("exhibit_id <=", value, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdIn(List<Long> values) {
            addCriterion("exhibit_id in", values, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdNotIn(List<Long> values) {
            addCriterion("exhibit_id not in", values, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdBetween(Long value1, Long value2) {
            addCriterion("exhibit_id between", value1, value2, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitIdNotBetween(Long value1, Long value2) {
            addCriterion("exhibit_id not between", value1, value2, "exhibitId");
            return (Criteria) this;
        }

        public Criteria andExhibitNameIsNull() {
            addCriterion("exhibit_name is null");
            return (Criteria) this;
        }

        public Criteria andExhibitNameIsNotNull() {
            addCriterion("exhibit_name is not null");
            return (Criteria) this;
        }

        public Criteria andExhibitNameEqualTo(String value) {
            addCriterion("exhibit_name =", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameNotEqualTo(String value) {
            addCriterion("exhibit_name <>", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameGreaterThan(String value) {
            addCriterion("exhibit_name >", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameGreaterThanOrEqualTo(String value) {
            addCriterion("exhibit_name >=", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameLessThan(String value) {
            addCriterion("exhibit_name <", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameLessThanOrEqualTo(String value) {
            addCriterion("exhibit_name <=", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameLike(String value) {
            addCriterion("exhibit_name like", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameNotLike(String value) {
            addCriterion("exhibit_name not like", value, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameIn(List<String> values) {
            addCriterion("exhibit_name in", values, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameNotIn(List<String> values) {
            addCriterion("exhibit_name not in", values, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameBetween(String value1, String value2) {
            addCriterion("exhibit_name between", value1, value2, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andExhibitNameNotBetween(String value1, String value2) {
            addCriterion("exhibit_name not between", value1, value2, "exhibitName");
            return (Criteria) this;
        }

        public Criteria andWordIdIsNull() {
            addCriterion("word_id is null");
            return (Criteria) this;
        }

        public Criteria andWordIdIsNotNull() {
            addCriterion("word_id is not null");
            return (Criteria) this;
        }

        public Criteria andWordIdEqualTo(Long value) {
            addCriterion("word_id =", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotEqualTo(Long value) {
            addCriterion("word_id <>", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdGreaterThan(Long value) {
            addCriterion("word_id >", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("word_id >=", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdLessThan(Long value) {
            addCriterion("word_id <", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdLessThanOrEqualTo(Long value) {
            addCriterion("word_id <=", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdIn(List<Long> values) {
            addCriterion("word_id in", values, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotIn(List<Long> values) {
            addCriterion("word_id not in", values, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdBetween(Long value1, Long value2) {
            addCriterion("word_id between", value1, value2, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotBetween(Long value1, Long value2) {
            addCriterion("word_id not between", value1, value2, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIsNull() {
            addCriterion("word is null");
            return (Criteria) this;
        }

        public Criteria andWordIsNotNull() {
            addCriterion("word is not null");
            return (Criteria) this;
        }

        public Criteria andWordEqualTo(String value) {
            addCriterion("word =", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotEqualTo(String value) {
            addCriterion("word <>", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThan(String value) {
            addCriterion("word >", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThanOrEqualTo(String value) {
            addCriterion("word >=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThan(String value) {
            addCriterion("word <", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThanOrEqualTo(String value) {
            addCriterion("word <=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLike(String value) {
            addCriterion("word like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotLike(String value) {
            addCriterion("word not like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordIn(List<String> values) {
            addCriterion("word in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotIn(List<String> values) {
            addCriterion("word not in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordBetween(String value1, String value2) {
            addCriterion("word between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotBetween(String value1, String value2) {
            addCriterion("word not between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andInNumberIsNull() {
            addCriterion("in_number is null");
            return (Criteria) this;
        }

        public Criteria andInNumberIsNotNull() {
            addCriterion("in_number is not null");
            return (Criteria) this;
        }

        public Criteria andInNumberEqualTo(Long value) {
            addCriterion("in_number =", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberNotEqualTo(Long value) {
            addCriterion("in_number <>", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberGreaterThan(Long value) {
            addCriterion("in_number >", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("in_number >=", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberLessThan(Long value) {
            addCriterion("in_number <", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberLessThanOrEqualTo(Long value) {
            addCriterion("in_number <=", value, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberIn(List<Long> values) {
            addCriterion("in_number in", values, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberNotIn(List<Long> values) {
            addCriterion("in_number not in", values, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberBetween(Long value1, Long value2) {
            addCriterion("in_number between", value1, value2, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInNumberNotBetween(Long value1, Long value2) {
            addCriterion("in_number not between", value1, value2, "inNumber");
            return (Criteria) this;
        }

        public Criteria andInDateIsNull() {
            addCriterion("in_date is null");
            return (Criteria) this;
        }

        public Criteria andInDateIsNotNull() {
            addCriterion("in_date is not null");
            return (Criteria) this;
        }

        public Criteria andInDateEqualTo(Date value) {
            addCriterion("in_date =", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotEqualTo(Date value) {
            addCriterion("in_date <>", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThan(Date value) {
            addCriterion("in_date >", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThanOrEqualTo(Date value) {
            addCriterion("in_date >=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThan(Date value) {
            addCriterion("in_date <", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThanOrEqualTo(Date value) {
            addCriterion("in_date <=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateIn(List<Date> values) {
            addCriterion("in_date in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotIn(List<Date> values) {
            addCriterion("in_date not in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateBetween(Date value1, Date value2) {
            addCriterion("in_date between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotBetween(Date value1, Date value2) {
            addCriterion("in_date not between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andInUserIsNull() {
            addCriterion("in_user is null");
            return (Criteria) this;
        }

        public Criteria andInUserIsNotNull() {
            addCriterion("in_user is not null");
            return (Criteria) this;
        }

        public Criteria andInUserEqualTo(String value) {
            addCriterion("in_user =", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotEqualTo(String value) {
            addCriterion("in_user <>", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserGreaterThan(String value) {
            addCriterion("in_user >", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserGreaterThanOrEqualTo(String value) {
            addCriterion("in_user >=", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLessThan(String value) {
            addCriterion("in_user <", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLessThanOrEqualTo(String value) {
            addCriterion("in_user <=", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLike(String value) {
            addCriterion("in_user like", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotLike(String value) {
            addCriterion("in_user not like", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserIn(List<String> values) {
            addCriterion("in_user in", values, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotIn(List<String> values) {
            addCriterion("in_user not in", values, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserBetween(String value1, String value2) {
            addCriterion("in_user between", value1, value2, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotBetween(String value1, String value2) {
            addCriterion("in_user not between", value1, value2, "inUser");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_word_in
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 29 19:34:56 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_word_in
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}