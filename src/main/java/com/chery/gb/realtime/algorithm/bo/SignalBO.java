package com.chery.gb.realtime.algorithm.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 传感数据实体类（带 JSON 映射注解）
 */
@Data
public class SignalBO {

    @JsonProperty("2001")
    private Integer _2001;

    @JsonProperty("2009")
    private Integer _2009;

    @JsonProperty("2076")
    private String _2076;

    @JsonProperty("2142")
    private Integer _2142;

    @JsonProperty("2143")
    private Integer _2143;

    @JsonProperty("2144")
    private Integer _2144;

    @JsonProperty("2145")
    private Integer _2145;

    @JsonProperty("2146")
    private Integer _2146;

    @JsonProperty("2147")
    private Integer _2147;

    @JsonProperty("2148")
    private Integer _2148;

    @JsonProperty("2149")
    private Integer _2149;

    @JsonProperty("2150")
    private Integer _2150;

    @JsonProperty("2151")
    private Integer _2151;

    @JsonProperty("2152")
    private Integer _2152;

    @JsonProperty("2153")
    private Integer _2153;

    @JsonProperty("2154")
    private Integer _2154;

    @JsonProperty("2155")
    private Integer _2155;

    @JsonProperty("2156")
    private Integer _2156;

    @JsonProperty("2157")
    private Integer _2157;

    @JsonProperty("2160")
    private Integer _2160;

    @JsonProperty("2187")
    private Integer _2187;

    @JsonProperty("2188")
    private Integer _2188;

    @JsonProperty("2189")
    private Integer _2189;

    @JsonProperty("2208")
    private Integer _2208;

    @JsonProperty("2209")
    private Integer _2209;

    @JsonProperty("2232")
    private Integer _2232;

    @JsonProperty("2277")
    private Integer _2277;

    @JsonProperty("2278")
    private Integer _2278;

    @JsonProperty("command")
    private Integer command;

    @JsonProperty("vin")
    private String vin;

    @JsonProperty("seq")
    private Integer seq;

    @JsonProperty("ct")
    private Long ct;

    @JsonProperty("21AB")
    private Integer _21AB;

    @JsonProperty("220C")
    private Integer _220C;

    @JsonProperty("21AA")
    private Integer _21AA;

    @JsonProperty("215C")
    private Integer _215C;

    @JsonProperty("215D")
    private Integer _215D;

    @JsonProperty("219D")
    private Integer _219D;

    @JsonProperty("219E")
    private Integer _219E;

    @JsonProperty("21B1")
    private Integer _21B1;

    @JsonProperty("21B2")
    private Integer _21B2;

    @JsonProperty("1BC1")
    private List<B1BC1> b1BC1;

    @JsonProperty("200F")
    private Integer _200F;

    @JsonProperty("219F")
    private Integer _219F;

    @JsonProperty("220D")
    private Integer _220D;

    @JsonProperty("207F")
    private Integer _207F;

    @JsonProperty("207A")
    private Long _207A;

    @JsonProperty("207B")
    private Long _207B;

    @JsonProperty("20FC")
    private Integer _20FC;

    @JsonProperty("20FD")
    private Integer _20FD;

    @JsonProperty("20FA")
    private Integer _20FA;

    @JsonProperty("20FB")
    private Integer _20FB;

    @JsonProperty("220B")
    private Integer _220B;

    @JsonProperty("21C2")
    private Integer _21C2;

    @JsonProperty("214F")
    private Integer _214F;

    @JsonProperty("214E")
    private Integer _214E;

    @JsonProperty("214D")
    private Integer _214D;

    @JsonProperty("214C")
    private Integer _214C;

    @JsonProperty("214B")
    private Integer _214B;

    @JsonProperty("214A")
    private Integer _214A;

    @JsonProperty("1BC2")
    private List<B1BC2> b1BC2;

    @JsonProperty("1BC3")
    private List<B1BC3> b1BC3;

    @JsonProperty("24C9")
    private Integer _24C9;

    @JsonProperty("20FE")
    private Integer _20FE;

    @JsonProperty("st")
    private Long st;

    /**
     * 1BC1 子结构
     */
    @Data
    public static class B1BC1 {
        @JsonProperty("21CD")
        private Integer _21CD;

        @JsonProperty("21CE")
        private Integer _21CE;

        @JsonProperty("21CF")
        private Integer _21CF;

        @JsonProperty("21D0")
        private Integer _21D0;

        @JsonProperty("21D1")
        private Integer _21D1;

        @JsonProperty("21D2")
        private Integer _21D2;

        @JsonProperty("21D3")
        private Integer _21D3;

        @JsonProperty("21D4")
        private Integer _21D4;
    }

    /**
     * 1BC2 子结构
     */
    @Data
    public static class B1BC2 {
        @JsonProperty("2100")
        private Integer _2100;

        @JsonProperty("2141")
        private Integer _2141;

        @JsonProperty("2158")
        private Integer _2158;

        @JsonProperty("2159")
        private Integer _2159;

        @JsonProperty("220E")
        private List<Integer> _220E;

        @JsonProperty("22CA")
        private Integer _22CA;
    }

    /**
     * 1BC3 子结构
     */
    @Data
    public static class B1BC3 {
        @JsonProperty("2101")
        private Integer _2101;

        @JsonProperty("220F")
        private List<Integer> _220F;
    }
}
