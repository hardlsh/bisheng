package com.bisheng.services.exhibit.model.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoothWordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public BoothWordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
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
     * This method corresponds to the database table tb_booth_word
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
     * This method corresponds to the database table tb_booth_word
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth_word
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
     * This class corresponds to the database table tb_booth_word
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

        public Criteria andBoothWordIdIsNull() {
            addCriterion("booth_word_id is null");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdIsNotNull() {
            addCriterion("booth_word_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdEqualTo(Long value) {
            addCriterion("booth_word_id =", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdNotEqualTo(Long value) {
            addCriterion("booth_word_id <>", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdGreaterThan(Long value) {
            addCriterion("booth_word_id >", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("booth_word_id >=", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdLessThan(Long value) {
            addCriterion("booth_word_id <", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdLessThanOrEqualTo(Long value) {
            addCriterion("booth_word_id <=", value, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdIn(List<Long> values) {
            addCriterion("booth_word_id in", values, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdNotIn(List<Long> values) {
            addCriterion("booth_word_id not in", values, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdBetween(Long value1, Long value2) {
            addCriterion("booth_word_id between", value1, value2, "boothWordId");
            return (Criteria) this;
        }

        public Criteria andBoothWordIdNotBetween(Long value1, Long value2) {
            addCriterion("booth_word_id not between", value1, value2, "boothWordId");
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

        public Criteria andTempletNameIsNull() {
            addCriterion("templet_name is null");
            return (Criteria) this;
        }

        public Criteria andTempletNameIsNotNull() {
            addCriterion("templet_name is not null");
            return (Criteria) this;
        }

        public Criteria andTempletNameEqualTo(String value) {
            addCriterion("templet_name =", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameNotEqualTo(String value) {
            addCriterion("templet_name <>", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameGreaterThan(String value) {
            addCriterion("templet_name >", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameGreaterThanOrEqualTo(String value) {
            addCriterion("templet_name >=", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameLessThan(String value) {
            addCriterion("templet_name <", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameLessThanOrEqualTo(String value) {
            addCriterion("templet_name <=", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameLike(String value) {
            addCriterion("templet_name like", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameNotLike(String value) {
            addCriterion("templet_name not like", value, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameIn(List<String> values) {
            addCriterion("templet_name in", values, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameNotIn(List<String> values) {
            addCriterion("templet_name not in", values, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameBetween(String value1, String value2) {
            addCriterion("templet_name between", value1, value2, "templetName");
            return (Criteria) this;
        }

        public Criteria andTempletNameNotBetween(String value1, String value2) {
            addCriterion("templet_name not between", value1, value2, "templetName");
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

        public Criteria andBoothIdIsNull() {
            addCriterion("booth_id is null");
            return (Criteria) this;
        }

        public Criteria andBoothIdIsNotNull() {
            addCriterion("booth_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoothIdEqualTo(Long value) {
            addCriterion("booth_id =", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdNotEqualTo(Long value) {
            addCriterion("booth_id <>", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdGreaterThan(Long value) {
            addCriterion("booth_id >", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdGreaterThanOrEqualTo(Long value) {
            addCriterion("booth_id >=", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdLessThan(Long value) {
            addCriterion("booth_id <", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdLessThanOrEqualTo(Long value) {
            addCriterion("booth_id <=", value, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdIn(List<Long> values) {
            addCriterion("booth_id in", values, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdNotIn(List<Long> values) {
            addCriterion("booth_id not in", values, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdBetween(Long value1, Long value2) {
            addCriterion("booth_id between", value1, value2, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothIdNotBetween(Long value1, Long value2) {
            addCriterion("booth_id not between", value1, value2, "boothId");
            return (Criteria) this;
        }

        public Criteria andBoothNameIsNull() {
            addCriterion("booth_name is null");
            return (Criteria) this;
        }

        public Criteria andBoothNameIsNotNull() {
            addCriterion("booth_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoothNameEqualTo(String value) {
            addCriterion("booth_name =", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameNotEqualTo(String value) {
            addCriterion("booth_name <>", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameGreaterThan(String value) {
            addCriterion("booth_name >", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameGreaterThanOrEqualTo(String value) {
            addCriterion("booth_name >=", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameLessThan(String value) {
            addCriterion("booth_name <", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameLessThanOrEqualTo(String value) {
            addCriterion("booth_name <=", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameLike(String value) {
            addCriterion("booth_name like", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameNotLike(String value) {
            addCriterion("booth_name not like", value, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameIn(List<String> values) {
            addCriterion("booth_name in", values, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameNotIn(List<String> values) {
            addCriterion("booth_name not in", values, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameBetween(String value1, String value2) {
            addCriterion("booth_name between", value1, value2, "boothName");
            return (Criteria) this;
        }

        public Criteria andBoothNameNotBetween(String value1, String value2) {
            addCriterion("booth_name not between", value1, value2, "boothName");
            return (Criteria) this;
        }

        public Criteria andXAxisIsNull() {
            addCriterion("x_axis is null");
            return (Criteria) this;
        }

        public Criteria andXAxisIsNotNull() {
            addCriterion("x_axis is not null");
            return (Criteria) this;
        }

        public Criteria andXAxisEqualTo(Integer value) {
            addCriterion("x_axis =", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisNotEqualTo(Integer value) {
            addCriterion("x_axis <>", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisGreaterThan(Integer value) {
            addCriterion("x_axis >", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisGreaterThanOrEqualTo(Integer value) {
            addCriterion("x_axis >=", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisLessThan(Integer value) {
            addCriterion("x_axis <", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisLessThanOrEqualTo(Integer value) {
            addCriterion("x_axis <=", value, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisIn(List<Integer> values) {
            addCriterion("x_axis in", values, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisNotIn(List<Integer> values) {
            addCriterion("x_axis not in", values, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisBetween(Integer value1, Integer value2) {
            addCriterion("x_axis between", value1, value2, "xAxis");
            return (Criteria) this;
        }

        public Criteria andXAxisNotBetween(Integer value1, Integer value2) {
            addCriterion("x_axis not between", value1, value2, "xAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisIsNull() {
            addCriterion("y_axis is null");
            return (Criteria) this;
        }

        public Criteria andYAxisIsNotNull() {
            addCriterion("y_axis is not null");
            return (Criteria) this;
        }

        public Criteria andYAxisEqualTo(Integer value) {
            addCriterion("y_axis =", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisNotEqualTo(Integer value) {
            addCriterion("y_axis <>", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisGreaterThan(Integer value) {
            addCriterion("y_axis >", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisGreaterThanOrEqualTo(Integer value) {
            addCriterion("y_axis >=", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisLessThan(Integer value) {
            addCriterion("y_axis <", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisLessThanOrEqualTo(Integer value) {
            addCriterion("y_axis <=", value, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisIn(List<Integer> values) {
            addCriterion("y_axis in", values, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisNotIn(List<Integer> values) {
            addCriterion("y_axis not in", values, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisBetween(Integer value1, Integer value2) {
            addCriterion("y_axis between", value1, value2, "yAxis");
            return (Criteria) this;
        }

        public Criteria andYAxisNotBetween(Integer value1, Integer value2) {
            addCriterion("y_axis not between", value1, value2, "yAxis");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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
     * This class corresponds to the database table tb_booth_word
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
     * This class corresponds to the database table tb_booth_word
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