package seedu.foodrem.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodrem.model.item.Item;

/**
 * A utility class containing a list of {@code item} objects to be used in tests related to sorting.
 */
public class ItemsToSort {
    public static final Item NI_Q1_U114_B119_E18_P13_R3 = new ItemBuilder().withItemName("NI Q1 U114 B119 E18 P13 R3")
            .withItemQuantity("1")
            .withItemUnit("VWW")
            .withItemBoughtDate("07-08-2022")
            .withItemExpiryDate("18-04-2022")
            .withItemPrice("13")
            .withItemRemarks("ACU")
            .build();
    public static final Item NG_Q2_U124_B63_E65_P96_R133 = new ItemBuilder().withItemName("NG Q2 U124 B63 E65 P96 R133")
            .withItemQuantity("2")
            .withItemUnit("XGW")
            .withItemBoughtDate("07-06-2022")
            .withItemExpiryDate("09-06-2022")
            .withItemPrice("96")
            .withItemRemarks("YIN")
            .build();
    public static final Item NG_Q3_U62_B55_E31_P30_R48 = new ItemBuilder().withItemName("NG Q3 U62 B55 E31 P30 R48")
            .withItemQuantity("3")
            .withItemUnit("MBG")
            .withItemBoughtDate("27-05-2022")
            .withItemExpiryDate("03-05-2022")
            .withItemPrice("30")
            .withItemRemarks("IBO")
            .build();
    public static final Item NI_Q4_U87_B117_E107_P114_R56 = new ItemBuilder().withItemName(
                    "NI Q4 U87 B117 E107 P114 R56")
            .withItemQuantity("4")
            .withItemUnit("RVB")
            .withItemBoughtDate("05-08-2022")
            .withItemExpiryDate("23-07-2022")
            .withItemPrice("114")
            .withItemRemarks("JQM")
            .build();
    public static final Item NA_Q5_U93_B134_E122_P128_R55 = new ItemBuilder().withItemName(
                    "NA Q5 U93 B134 E122 P128 R55")
            .withItemQuantity("5")
            .withItemUnit("TAS")
            .withItemBoughtDate("22-08-2022")
            .withItemExpiryDate("10-08-2022")
            .withItemPrice("128")
            .withItemRemarks("JIT")
            .build();
    public static final Item NH_Q6_U80_B106_E44_P137_R4 = new ItemBuilder().withItemName("NH Q6 U80 B106 E44 P137 R4")
            .withItemQuantity("6")
            .withItemUnit("QPI")
            .withItemBoughtDate("22-07-2022")
            .withItemExpiryDate("16-05-2022")
            .withItemPrice("137")
            .withItemRemarks("AJT")
            .build();
    public static final Item NJ_Q7_U120_B25_E49_P66_R59 = new ItemBuilder().withItemName("NJ Q7 U120 B25 E49 P66 R59")
            .withItemQuantity("7")
            .withItemUnit("WUG")
            .withItemBoughtDate("25-04-2022")
            .withItemExpiryDate("21-05-2022")
            .withItemPrice("66")
            .withItemRemarks("KTU")
            .build();
    public static final Item NA_Q8_U14_B87_E57_P49_R14 = new ItemBuilder().withItemName("NA Q8 U14 B87 E57 P49 R14")
            .withItemQuantity("8")
            .withItemUnit("BWF")
            .withItemBoughtDate("03-07-2022")
            .withItemExpiryDate("01-06-2022")
            .withItemPrice("49")
            .withItemRemarks("BPQ")
            .build();
    public static final Item NC_Q9_U4_B28_E28_P7_R7 = new ItemBuilder().withItemName("NC Q9 U4 B28 E28 P7 R7")
            .withItemQuantity("9")
            .withItemUnit("ATT")
            .withItemBoughtDate("28-04-2022")
            .withItemExpiryDate("28-04-2022")
            .withItemPrice("7")
            .withItemRemarks("ATN")
            .build();
    public static final Item NK_Q10_U32_B23_E23_P1_R84 = new ItemBuilder().withItemName("NK Q10 U32 B23 E23 P1 R84")
            .withItemQuantity("10")
            .withItemUnit("FTT")
            .withItemBoughtDate("23-04-2022")
            .withItemExpiryDate("23-04-2022")
            .withItemPrice("1")
            .withItemRemarks("PWJ")
            .build();
    public static final Item NB_Q11_U94_B40_E78_P97_R104 = new ItemBuilder().withItemName("NB Q11 U94 B40 E78 P97 R104")
            .withItemQuantity("11")
            .withItemUnit("TEO")
            .withItemBoughtDate("12-05-2022")
            .withItemExpiryDate("22-06-2022")
            .withItemPrice("97")
            .withItemRemarks("TQT")
            .build();
    public static final Item NK_Q12_U91_B62_E60_P41_R18 = new ItemBuilder().withItemName("NK Q12 U91 B62 E60 P41 R18")
            .withItemQuantity("12")
            .withItemUnit("SOF")
            .withItemBoughtDate("06-06-2022")
            .withItemExpiryDate("04-06-2022")
            .withItemPrice("41")
            .withItemRemarks("DXN")
            .build();
    public static final Item NA_Q13_U59_B17_E39_P58_R24 = new ItemBuilder().withItemName("NA Q13 U59 B17 E39 P58 R24")
            .withItemQuantity("13")
            .withItemUnit("LRJ")
            .withItemBoughtDate("17-04-2022")
            .withItemExpiryDate("11-05-2022")
            .withItemPrice("58")
            .withItemRemarks("ESW")
            .build();
    public static final Item ND_Q14_U44_B136_E128_P132_R43 = new ItemBuilder().withItemName(
                    "ND Q14 U44 B136 E128 P132 R43")
            .withItemQuantity("14")
            .withItemUnit("HUW")
            .withItemBoughtDate("24-08-2022")
            .withItemExpiryDate("16-08-2022")
            .withItemPrice("132")
            .withItemRemarks("HPI")
            .build();
    public static final Item ND_Q15_U53_B120_E55_P103_R135 = new ItemBuilder().withItemName(
                    "ND Q15 U53 B120 E55 P103 R135")
            .withItemQuantity("15")
            .withItemUnit("KDZ")
            .withItemBoughtDate("08-08-2022")
            .withItemExpiryDate("27-05-2022")
            .withItemPrice("103")
            .withItemRemarks("YQU")
            .build();
    public static final Item NI_Q16_U77_B61_E130_P123_R137 = new ItemBuilder().withItemName(
                    "NI Q16 U77 B61 E130 P123 R137")
            .withItemQuantity("16")
            .withItemUnit("PZW")
            .withItemBoughtDate("05-06-2022")
            .withItemExpiryDate("18-08-2022")
            .withItemPrice("123")
            .withItemRemarks("ZBR")
            .build();
    public static final Item NJ_Q17_U37_B12_E103_P45_R31 = new ItemBuilder().withItemName("NJ Q17 U37 B12 E103 P45 R31")
            .withItemQuantity("17")
            .withItemUnit("GKE")
            .withItemBoughtDate("12-04-2022")
            .withItemExpiryDate("19-07-2022")
            .withItemPrice("45")
            .withItemRemarks("FNY")
            .build();
    public static final Item NA_Q18_U11_B47_E92_P98_R108 = new ItemBuilder().withItemName("NA Q18 U11 B47 E92 P98 R108")
            .withItemQuantity("18")
            .withItemUnit("BTU")
            .withItemBoughtDate("19-05-2022")
            .withItemExpiryDate("08-07-2022")
            .withItemPrice("98")
            .withItemRemarks("TUP")
            .build();
    public static final Item NC_Q19_U69_B109_E66_P81_R50 = new ItemBuilder().withItemName("NC Q19 U69 B109 E66 P81 R50")
            .withItemQuantity("19")
            .withItemUnit("ORL")
            .withItemBoughtDate("25-07-2022")
            .withItemExpiryDate("10-06-2022")
            .withItemPrice("81")
            .withItemRemarks("ISJ")
            .build();
    public static final Item NK_Q20_U60_B110_E24_P118_R81 = new ItemBuilder().withItemName(
                    "NK Q20 U60 B110 E24 P118 R81")
            .withItemQuantity("20")
            .withItemUnit("LVE")
            .withItemBoughtDate("26-07-2022")
            .withItemExpiryDate("24-04-2022")
            .withItemPrice("118")
            .withItemRemarks("OXJ")
            .build();
    public static final Item NK_Q21_U112_B118_E42_P8_R142 = new ItemBuilder().withItemName(
                    "NK Q21 U112 B118 E42 P8 R142")
            .withItemQuantity("21")
            .withItemUnit("VUC")
            .withItemBoughtDate("06-08-2022")
            .withItemExpiryDate("14-05-2022")
            .withItemPrice("8")
            .withItemRemarks("ZJA")
            .build();
    public static final Item NE_Q22_U89_B133_E110_P92_R15 = new ItemBuilder().withItemName(
                    "NE Q22 U89 B133 E110 P92 R15")
            .withItemQuantity("22")
            .withItemUnit("SHN")
            .withItemBoughtDate("21-08-2022")
            .withItemExpiryDate("26-07-2022")
            .withItemPrice("92")
            .withItemRemarks("BTK")
            .build();
    public static final Item NF_Q23_U1_B82_E59_P116_R139 = new ItemBuilder().withItemName("NF Q23 U1 B82 E59 P116 R139")
            .withItemQuantity("23")
            .withItemUnit("AAN")
            .withItemBoughtDate("26-06-2022")
            .withItemExpiryDate("03-06-2022")
            .withItemPrice("116")
            .withItemRemarks("ZDJ")
            .build();
    public static final Item NL_Q25_U5_B96_E89_P60_R103 = new ItemBuilder().withItemName("NL Q25 U5 B96 E89 P60 R103")
            .withItemQuantity("25")
            .withItemUnit("AUX")
            .withItemBoughtDate("12-07-2022")
            .withItemExpiryDate("05-07-2022")
            .withItemPrice("60")
            .withItemRemarks("TJT")
            .build();
    public static final Item NA_Q24_U104_B130_E90_P94_R74 = new ItemBuilder().withItemName(
                    "NA Q24 U104 B130 E90 P94 R74")
            .withItemQuantity("24")
            .withItemUnit("UTM")
            .withItemBoughtDate("18-08-2022")
            .withItemExpiryDate("06-07-2022")
            .withItemPrice("94")
            .withItemRemarks("NDM")
            .build();
    public static final Item ND_Q26_U135_B83_E113_P20_R44 = new ItemBuilder().withItemName(
                    "ND Q26 U135 B83 E113 P20 R44")
            .withItemQuantity("26")
            .withItemUnit("YYK")
            .withItemBoughtDate("27-06-2022")
            .withItemExpiryDate("01-08-2022")
            .withItemPrice("20")
            .withItemRemarks("HQF")
            .build();
    public static final Item NB_Q27_U40_B113_E85_P115_R87 = new ItemBuilder().withItemName(
                    "NB Q27 U40 B113 E85 P115 R87")
            .withItemQuantity("27")
            .withItemUnit("GUO")
            .withItemBoughtDate("01-08-2022")
            .withItemExpiryDate("01-07-2022")
            .withItemPrice("115")
            .withItemRemarks("QPR")
            .build();
    public static final Item NC_Q28_U29_B112_E73_P14_R130 = new ItemBuilder().withItemName(
                    "NC Q28 U29 B112 E73 P14 R130")
            .withItemQuantity("28")
            .withItemUnit("FOX")
            .withItemBoughtDate("28-07-2022")
            .withItemExpiryDate("17-06-2022")
            .withItemPrice("14")
            .withItemRemarks("YCE")
            .build();
    public static final Item NC_Q29_U79_B126_E123_P135_R63 = new ItemBuilder().withItemName(
                    "NC Q29 U79 B126 E123 P135 R63")
            .withItemQuantity("29")
            .withItemUnit("QNW")
            .withItemBoughtDate("14-08-2022")
            .withItemExpiryDate("11-08-2022")
            .withItemPrice("135")
            .withItemRemarks("LLL")
            .build();
    public static final Item NA_Q30_U45_B128_E82_P68_R116 = new ItemBuilder().withItemName(
                    "NA Q30 U45 B128 E82 P68 R116")
            .withItemQuantity("30")
            .withItemUnit("HVO")
            .withItemBoughtDate("16-08-2022")
            .withItemExpiryDate("26-06-2022")
            .withItemPrice("68")
            .withItemRemarks("VDK")
            .build();
    public static final Item NB_Q31_U20_B94_E138_P35_R123 = new ItemBuilder().withItemName(
                    "NB Q31 U20 B94 E138 P35 R123")
            .withItemQuantity("31")
            .withItemUnit("DHR")
            .withItemBoughtDate("10-07-2022")
            .withItemExpiryDate("26-08-2022")
            .withItemPrice("35")
            .withItemRemarks("WNU")
            .build();
    public static final Item NF_Q32_U113_B35_E96_P111_R98 = new ItemBuilder().withItemName(
                    "NF Q32 U113 B35 E96 P111 R98")
            .withItemQuantity("32")
            .withItemUnit("VWP")
            .withItemBoughtDate("07-05-2022")
            .withItemExpiryDate("12-07-2022")
            .withItemPrice("111")
            .withItemRemarks("SNH")
            .build();
    public static final Item ND_Q33_U15_B116_E91_P127_R39 = new ItemBuilder().withItemName(
                    "ND Q33 U15 B116 E91 P127 R39")
            .withItemQuantity("33")
            .withItemUnit("CJO")
            .withItemBoughtDate("04-08-2022")
            .withItemExpiryDate("07-07-2022")
            .withItemPrice("127")
            .withItemRemarks("GUB")
            .build();
    public static final Item NB_Q34_U16_B27_E98_P136_R111 = new ItemBuilder().withItemName(
                    "NB Q34 U16 B27 E98 P136 R111")
            .withItemQuantity("34")
            .withItemUnit("CKK")
            .withItemBoughtDate("27-04-2022")
            .withItemExpiryDate("14-07-2022")
            .withItemPrice("136")
            .withItemRemarks("UFA")
            .build();
    public static final Item NI_Q35_U100_B114_E81_P44_R114 = new ItemBuilder().withItemName(
                    "NI Q35 U100 B114 E81 P44 R114")
            .withItemQuantity("35")
            .withItemUnit("TWG")
            .withItemBoughtDate("02-08-2022")
            .withItemExpiryDate("25-06-2022")
            .withItemPrice("44")
            .withItemRemarks("UPN")
            .build();
    public static final Item NH_Q36_U73_B81_E16_P110_R86 = new ItemBuilder().withItemName("NH Q36 U73 B81 E16 P110 R86")
            .withItemQuantity("36")
            .withItemUnit("PFH")
            .withItemBoughtDate("25-06-2022")
            .withItemExpiryDate("16-04-2022")
            .withItemPrice("110")
            .withItemRemarks("QCK")
            .build();
    public static final Item NG_Q37_U108_B10_E8_P31_R127 = new ItemBuilder().withItemName("NG Q37 U108 B10 E8 P31 R127")
            .withItemQuantity("37")
            .withItemUnit("VJW")
            .withItemBoughtDate("10-04-2022")
            .withItemExpiryDate("08-04-2022")
            .withItemPrice("31")
            .withItemRemarks("XQD")
            .build();
    public static final Item NE_Q38_U19_B70_E7_P124_R36 = new ItemBuilder().withItemName("NE Q38 U19 B70 E7 P124 R36")
            .withItemQuantity("38")
            .withItemUnit("CWM")
            .withItemBoughtDate("14-06-2022")
            .withItemExpiryDate("07-04-2022")
            .withItemPrice("124")
            .withItemRemarks("GNY")
            .build();
    public static final Item NJ_Q39_U68_B14_E34_P88_R96 = new ItemBuilder().withItemName("NJ Q39 U68 B14 E34 P88 R96")
            .withItemQuantity("39")
            .withItemUnit("ODS")
            .withItemBoughtDate("14-04-2022")
            .withItemExpiryDate("06-05-2022")
            .withItemPrice("88")
            .withItemRemarks("SEA")
            .build();
    public static final Item NH_Q40_U96_B6_E140_P16_R68 = new ItemBuilder().withItemName("NH Q40 U96 B6 E140 P16 R68")
            .withItemQuantity("40")
            .withItemUnit("TNM")
            .withItemBoughtDate("06-04-2022")
            .withItemExpiryDate("28-08-2022")
            .withItemPrice("16")
            .withItemRemarks("MIJ")
            .build();
    public static final Item NK_Q41_U131_B4_E95_P37_R1 = new ItemBuilder().withItemName("NK Q41 U131 B4 E95 P37 R1")
            .withItemQuantity("41")
            .withItemUnit("YTP")
            .withItemBoughtDate("04-04-2022")
            .withItemExpiryDate("11-07-2022")
            .withItemPrice("37")
            .withItemRemarks("AAI")
            .build();
    public static final Item NA_Q42_U67_B53_E40_P65_R140 = new ItemBuilder().withItemName("NA Q42 U67 B53 E40 P65 R140")
            .withItemQuantity("42")
            .withItemUnit("OBF")
            .withItemBoughtDate("25-05-2022")
            .withItemExpiryDate("12-05-2022")
            .withItemPrice("65")
            .withItemRemarks("ZHF")
            .build();
    public static final Item NH_Q43_U65_B15_E141_P141_R72 = new ItemBuilder().withItemName(
                    "NH Q43 U65 B15 E141 P141 R72")
            .withItemQuantity("43")
            .withItemUnit("NFM")
            .withItemBoughtDate("15-04-2022")
            .withItemExpiryDate("01-09-2022")
            .withItemPrice("141")
            .withItemRemarks("MRJ")
            .build();
    public static final Item NH_Q44_U43_B69_E47_P100_R109 = new ItemBuilder().withItemName(
                    "NH Q44 U43 B69 E47 P100 R109")
            .withItemQuantity("44")
            .withItemUnit("HSL")
            .withItemBoughtDate("13-06-2022")
            .withItemExpiryDate("19-05-2022")
            .withItemPrice("100")
            .withItemRemarks("TZU")
            .build();
    public static final Item NK_Q45_U143_B111_E20_P24_R60 = new ItemBuilder().withItemName(
                    "NK Q45 U143 B111 E20 P24 R60")
            .withItemQuantity("45")
            .withItemUnit("ZWL")
            .withItemBoughtDate("27-07-2022")
            .withItemExpiryDate("20-04-2022")
            .withItemPrice("24")
            .withItemRemarks("LGP")
            .build();
    public static final Item ND_Q46_U74_B97_E48_P99_R12 = new ItemBuilder().withItemName("ND Q46 U74 B97 E48 P99 R12")
            .withItemQuantity("46")
            .withItemUnit("PKA")
            .withItemBoughtDate("13-07-2022")
            .withItemExpiryDate("20-05-2022")
            .withItemPrice("99")
            .withItemRemarks("BJN")
            .build();
    public static final Item NL_Q47_U81_B76_E77_P122_R93 = new ItemBuilder().withItemName("NL Q47 U81 B76 E77 P122 R93")
            .withItemQuantity("47")
            .withItemUnit("QSZ")
            .withItemBoughtDate("20-06-2022")
            .withItemExpiryDate("21-06-2022")
            .withItemPrice("122")
            .withItemRemarks("RNU")
            .build();
    public static final Item NB_Q48_U51_B51_E116_P17_R100 = new ItemBuilder().withItemName(
                    "NB Q48 U51 B51 E116 P17 R100")
            .withItemQuantity("48")
            .withItemUnit("JQV")
            .withItemBoughtDate("23-05-2022")
            .withItemExpiryDate("04-08-2022")
            .withItemPrice("17")
            .withItemRemarks("SWN")
            .build();
    public static final Item NG_Q49_U133_B86_E87_P46_R19 = new ItemBuilder().withItemName("NG Q49 U133 B86 E87 P46 R19")
            .withItemQuantity("49")
            .withItemUnit("YVS")
            .withItemBoughtDate("02-07-2022")
            .withItemExpiryDate("03-07-2022")
            .withItemPrice("46")
            .withItemRemarks("DZB")
            .build();
    public static final Item NL_Q50_U84_B60_E13_P48_R21 = new ItemBuilder().withItemName("NL Q50 U84 B60 E13 P48 R21")
            .withItemQuantity("50")
            .withItemUnit("RHV")
            .withItemBoughtDate("04-06-2022")
            .withItemExpiryDate("13-04-2022")
            .withItemPrice("48")
            .withItemRemarks("ECZ")
            .build();
    public static final Item NB_Q51_U10_B30_E1_P105_R94 = new ItemBuilder().withItemName("NB Q51 U10 B30 E1 P105 R94")
            .withItemQuantity("51")
            .withItemUnit("BET")
            .withItemBoughtDate("02-05-2022")
            .withItemExpiryDate("01-04-2022")
            .withItemPrice("105")
            .withItemRemarks("RQU")
            .build();
    public static final Item NI_Q52_U140_B77_E61_P71_R85 = new ItemBuilder().withItemName("NI Q52 U140 B77 E61 P71 R85")
            .withItemQuantity("52")
            .withItemUnit("ZLZ")
            .withItemBoughtDate("21-06-2022")
            .withItemExpiryDate("05-06-2022")
            .withItemPrice("71")
            .withItemRemarks("QAH")
            .build();
    public static final Item NE_Q53_U64_B44_E45_P95_R46 = new ItemBuilder().withItemName("NE Q53 U64 B44 E45 P95 R46")
            .withItemQuantity("53")
            .withItemUnit("MXC")
            .withItemBoughtDate("16-05-2022")
            .withItemExpiryDate("17-05-2022")
            .withItemPrice("95")
            .withItemRemarks("HWI")
            .build();
    public static final Item ND_Q54_U17_B104_E88_P3_R29 = new ItemBuilder().withItemName("ND Q54 U17 B104 E88 P3 R29")
            .withItemQuantity("54")
            .withItemUnit("CMD")
            .withItemBoughtDate("20-07-2022")
            .withItemExpiryDate("04-07-2022")
            .withItemPrice("3")
            .withItemRemarks("FNB")
            .build();
    public static final Item NG_Q55_U82_B141_E19_P85_R128 = new ItemBuilder().withItemName(
                    "NG Q55 U82 B141 E19 P85 R128")
            .withItemQuantity("55")
            .withItemUnit("QXZ")
            .withItemBoughtDate("01-09-2022")
            .withItemExpiryDate("19-04-2022")
            .withItemPrice("85")
            .withItemRemarks("XUP")
            .build();
    public static final Item NL_Q56_U105_B131_E22_P62_R6 = new ItemBuilder().withItemName("NL Q56 U105 B131 E22 P62 R6")
            .withItemQuantity("56")
            .withItemUnit("UUC")
            .withItemBoughtDate("19-08-2022")
            .withItemExpiryDate("22-04-2022")
            .withItemPrice("62")
            .withItemRemarks("AQO")
            .build();
    public static final Item NL_Q57_U75_B57_E101_P50_R30 = new ItemBuilder().withItemName("NL Q57 U75 B57 E101 P50 R30")
            .withItemQuantity("57")
            .withItemUnit("PLT")
            .withItemBoughtDate("01-06-2022")
            .withItemExpiryDate("17-07-2022")
            .withItemPrice("50")
            .withItemRemarks("FNB")
            .build();
    public static final Item NL_Q58_U101_B50_E75_P112_R5 = new ItemBuilder().withItemName("NL Q58 U101 B50 E75 P112 R5")
            .withItemQuantity("58")
            .withItemUnit("UCM")
            .withItemBoughtDate("22-05-2022")
            .withItemExpiryDate("19-06-2022")
            .withItemPrice("112")
            .withItemRemarks("ALF")
            .build();
    public static final Item NJ_Q59_U128_B137_E102_P78_R64 = new ItemBuilder().withItemName(
                    "NJ Q59 U128 B137 E102 P78 R64")
            .withItemQuantity("59")
            .withItemUnit("YDH")
            .withItemBoughtDate("25-08-2022")
            .withItemExpiryDate("18-07-2022")
            .withItemPrice("78")
            .withItemRemarks("LLP")
            .build();
    public static final Item NC_Q60_U85_B7_E118_P86_R132 = new ItemBuilder().withItemName("NC Q60 U85 B7 E118 P86 R132")
            .withItemQuantity("60")
            .withItemUnit("RIU")
            .withItemBoughtDate("07-04-2022")
            .withItemExpiryDate("06-08-2022")
            .withItemPrice("86")
            .withItemRemarks("YHO")
            .build();
    public static final Item NF_Q61_U34_B79_E129_P28_R107 = new ItemBuilder().withItemName(
                    "NF Q61 U34 B79 E129 P28 R107")
            .withItemQuantity("61")
            .withItemUnit("FYL")
            .withItemBoughtDate("23-06-2022")
            .withItemExpiryDate("17-08-2022")
            .withItemPrice("28")
            .withItemRemarks("TRG")
            .build();
    public static final Item NK_Q62_U47_B29_E62_P26_R23 = new ItemBuilder().withItemName("NK Q62 U47 B29 E62 P26 R23")
            .withItemQuantity("62")
            .withItemUnit("HYH")
            .withItemBoughtDate("01-05-2022")
            .withItemExpiryDate("06-06-2022")
            .withItemPrice("26")
            .withItemRemarks("EPO")
            .build();
    public static final Item NJ_Q63_U18_B72_E52_P12_R66 = new ItemBuilder().withItemName("NJ Q63 U18 B72 E52 P12 R66")
            .withItemQuantity("63")
            .withItemUnit("CME")
            .withItemBoughtDate("16-06-2022")
            .withItemExpiryDate("24-05-2022")
            .withItemPrice("12")
            .withItemRemarks("LZV")
            .build();
    public static final Item NB_Q64_U110_B31_E5_P67_R91 = new ItemBuilder().withItemName("NB Q64 U110 B31 E5 P67 R91")
            .withItemQuantity("64")
            .withItemUnit("VMW")
            .withItemBoughtDate("03-05-2022")
            .withItemExpiryDate("05-04-2022")
            .withItemPrice("67")
            .withItemRemarks("REW")
            .build();
    public static final Item NL_Q65_U36_B88_E133_P4_R136 = new ItemBuilder().withItemName("NL Q65 U36 B88 E133 P4 R136")
            .withItemQuantity("65")
            .withItemUnit("GHF")
            .withItemBoughtDate("04-07-2022")
            .withItemExpiryDate("21-08-2022")
            .withItemPrice("4")
            .withItemRemarks("YRB")
            .build();
    public static final Item NE_Q66_U27_B132_E117_P83_R54 = new ItemBuilder().withItemName(
                    "NE Q66 U27 B132 E117 P83 R54")
            .withItemQuantity("66")
            .withItemUnit("FHL")
            .withItemBoughtDate("20-08-2022")
            .withItemExpiryDate("05-08-2022")
            .withItemPrice("83")
            .withItemRemarks("JDQ")
            .build();
    public static final Item NK_Q67_U139_B26_E15_P57_R37 = new ItemBuilder().withItemName("NK Q67 U139 B26 E15 P57 R37")
            .withItemQuantity("67")
            .withItemUnit("ZGZ")
            .withItemBoughtDate("26-04-2022")
            .withItemExpiryDate("15-04-2022")
            .withItemPrice("57")
            .withItemRemarks("GOE")
            .build();
    public static final Item NF_Q68_U31_B101_E2_P93_R131 = new ItemBuilder().withItemName("NF Q68 U31 B101 E2 P93 R131")
            .withItemQuantity("68")
            .withItemUnit("FQV")
            .withItemBoughtDate("17-07-2022")
            .withItemExpiryDate("02-04-2022")
            .withItemPrice("93")
            .withItemRemarks("YET")
            .build();
    public static final Item NI_Q69_U127_B9_E72_P53_R77 = new ItemBuilder().withItemName("NI Q69 U127 B9 E72 P53 R77")
            .withItemQuantity("69")
            .withItemUnit("XYL")
            .withItemBoughtDate("09-04-2022")
            .withItemExpiryDate("16-06-2022")
            .withItemPrice("53")
            .withItemRemarks("NWZ")
            .build();
    public static final Item NJ_Q70_U24_B102_E21_P143_R76 = new ItemBuilder().withItemName(
                    "NJ Q70 U24 B102 E21 P143 R76")
            .withItemQuantity("70")
            .withItemUnit("EDR")
            .withItemBoughtDate("18-07-2022")
            .withItemExpiryDate("21-04-2022")
            .withItemPrice("143")
            .withItemRemarks("NQD")
            .build();
    public static final Item NI_Q71_U50_B32_E80_P27_R80 = new ItemBuilder().withItemName("NI Q71 U50 B32 E80 P27 R80")
            .withItemQuantity("71")
            .withItemUnit("JHP")
            .withItemBoughtDate("04-05-2022")
            .withItemExpiryDate("24-06-2022")
            .withItemPrice("27")
            .withItemRemarks("OTP")
            .build();
    public static final Item NK_Q72_U136_B95_E111_P43_R113 = new ItemBuilder().withItemName(
                    "NK Q72 U136 B95 E111 P43 R113")
            .withItemQuantity("72")
            .withItemUnit("YYN")
            .withItemBoughtDate("11-07-2022")
            .withItemExpiryDate("27-07-2022")
            .withItemPrice("43")
            .withItemRemarks("UPM")
            .build();
    public static final Item NL_Q73_U23_B5_E32_P82_R8 = new ItemBuilder().withItemName("NL Q73 U23 B5 E32 P82 R8")
            .withItemQuantity("73")
            .withItemUnit("ECA")
            .withItemBoughtDate("05-04-2022")
            .withItemExpiryDate("04-05-2022")
            .withItemPrice("82")
            .withItemRemarks("AZM")
            .build();
    public static final Item NF_Q74_U98_B48_E70_P42_R20 = new ItemBuilder().withItemName("NF Q74 U98 B48 E70 P42 R20")
            .withItemQuantity("74")
            .withItemUnit("TRV")
            .withItemBoughtDate("20-05-2022")
            .withItemExpiryDate("14-06-2022")
            .withItemPrice("42")
            .withItemRemarks("ECM")
            .build();
    public static final Item NC_Q75_U30_B2_E86_P77_R47 = new ItemBuilder().withItemName("NC Q75 U30 B2 E86 P77 R47")
            .withItemQuantity("75")
            .withItemUnit("FQQ")
            .withItemBoughtDate("02-04-2022")
            .withItemExpiryDate("02-07-2022")
            .withItemPrice("77")
            .withItemRemarks("HXA")
            .build();
    public static final Item NA_Q76_U2_B98_E114_P19_R117 = new ItemBuilder().withItemName("NA Q76 U2 B98 E114 P19 R117")
            .withItemQuantity("76")
            .withItemUnit("AJA")
            .withItemBoughtDate("14-07-2022")
            .withItemExpiryDate("02-08-2022")
            .withItemPrice("19")
            .withItemRemarks("VKZ")
            .build();
    public static final Item NF_Q77_U123_B3_E4_P59_R25 = new ItemBuilder().withItemName("NF Q77 U123 B3 E4 P59 R25")
            .withItemQuantity("77")
            .withItemUnit("XFK")
            .withItemBoughtDate("03-04-2022")
            .withItemExpiryDate("04-04-2022")
            .withItemPrice("59")
            .withItemRemarks("ETA")
            .build();
    public static final Item NC_Q78_U71_B85_E56_P106_R134 = new ItemBuilder().withItemName(
                    "NC Q78 U71 B85 E56 P106 R134")
            .withItemQuantity("78")
            .withItemUnit("PBH")
            .withItemBoughtDate("01-07-2022")
            .withItemExpiryDate("28-05-2022")
            .withItemPrice("106")
            .withItemRemarks("YOB")
            .build();
    public static final Item NC_Q79_U58_B103_E14_P52_R61 = new ItemBuilder().withItemName("NC Q79 U58 B103 E14 P52 R61")
            .withItemQuantity("79")
            .withItemUnit("LCN")
            .withItemBoughtDate("19-07-2022")
            .withItemExpiryDate("14-04-2022")
            .withItemPrice("52")
            .withItemRemarks("LJV")
            .build();
    public static final Item NE_Q80_U99_B16_E120_P142_R82 = new ItemBuilder().withItemName(
                    "NE Q80 U99 B16 E120 P142 R82")
            .withItemQuantity("80")
            .withItemUnit("TTC")
            .withItemBoughtDate("16-04-2022")
            .withItemExpiryDate("08-08-2022")
            .withItemPrice("142")
            .withItemRemarks("PMX")
            .build();
    public static final Item NF_Q81_U141_B122_E58_P29_R79 = new ItemBuilder().withItemName(
                    "NF Q81 U141 B122 E58 P29 R79")
            .withItemQuantity("81")
            .withItemUnit("ZML")
            .withItemBoughtDate("10-08-2022")
            .withItemExpiryDate("02-06-2022")
            .withItemPrice("29")
            .withItemRemarks("OLU")
            .build();
    public static final Item NF_Q82_U125_B34_E76_P129_R28 = new ItemBuilder().withItemName(
                    "NF Q82 U125 B34 E76 P129 R28")
            .withItemQuantity("82")
            .withItemUnit("XQW")
            .withItemBoughtDate("06-05-2022")
            .withItemExpiryDate("20-06-2022")
            .withItemPrice("129")
            .withItemRemarks("FFT")
            .build();
    public static final Item NB_Q83_U63_B33_E104_P130_R62 = new ItemBuilder().withItemName(
                    "NB Q83 U63 B33 E104 P130 R62")
            .withItemQuantity("83")
            .withItemUnit("MFD")
            .withItemBoughtDate("05-05-2022")
            .withItemExpiryDate("20-07-2022")
            .withItemPrice("130")
            .withItemRemarks("LKZ")
            .build();
    public static final Item NH_Q84_U13_B52_E41_P72_R78 = new ItemBuilder().withItemName("NH Q84 U13 B52 E41 P72 R78")
            .withItemQuantity("84")
            .withItemUnit("BVP")
            .withItemBoughtDate("24-05-2022")
            .withItemExpiryDate("13-05-2022")
            .withItemPrice("72")
            .withItemRemarks("OFY")
            .build();
    public static final Item NB_Q85_U144_B56_E11_P10_R40 = new ItemBuilder().withItemName("NB Q85 U144 B56 E11 P10 R40")
            .withItemQuantity("85")
            .withItemUnit("ZXV")
            .withItemBoughtDate("28-05-2022")
            .withItemExpiryDate("11-04-2022")
            .withItemPrice("10")
            .withItemRemarks("GVN")
            .build();
    public static final Item NJ_Q86_U52_B92_E112_P39_R124 = new ItemBuilder().withItemName(
                    "NJ Q86 U52 B92 E112 P39 R124")
            .withItemQuantity("86")
            .withItemUnit("JRP")
            .withItemBoughtDate("08-07-2022")
            .withItemExpiryDate("28-07-2022")
            .withItemPrice("39")
            .withItemRemarks("WSN")
            .build();
    public static final Item NJ_Q87_U122_B90_E134_P21_R119 = new ItemBuilder().withItemName(
                    "NJ Q87 U122 B90 E134 P21 R119")
            .withItemQuantity("87")
            .withItemUnit("XCH")
            .withItemBoughtDate("06-07-2022")
            .withItemExpiryDate("22-08-2022")
            .withItemPrice("21")
            .withItemRemarks("VSV")
            .build();
    public static final Item NJ_Q88_U90_B143_E97_P5_R138 = new ItemBuilder().withItemName("NJ Q88 U90 B143 E97 P5 R138")
            .withItemQuantity("88")
            .withItemUnit("SKB")
            .withItemBoughtDate("03-09-2022")
            .withItemExpiryDate("13-07-2022")
            .withItemPrice("5")
            .withItemRemarks("ZDI")
            .build();
    public static final Item ND_Q89_U7_B74_E37_P11_R101 = new ItemBuilder().withItemName("ND Q89 U7 B74 E37 P11 R101")
            .withItemQuantity("89")
            .withItemUnit("AYG")
            .withItemBoughtDate("18-06-2022")
            .withItemExpiryDate("09-05-2022")
            .withItemPrice("11")
            .withItemRemarks("TBK")
            .build();
    public static final Item NI_Q90_U126_B108_E3_P38_R52 = new ItemBuilder().withItemName("NI Q90 U126 B108 E3 P38 R52")
            .withItemQuantity("90")
            .withItemUnit("XUF")
            .withItemBoughtDate("24-07-2022")
            .withItemExpiryDate("03-04-2022")
            .withItemPrice("38")
            .withItemRemarks("IXR")
            .build();
    public static final Item NF_Q91_U132_B11_E84_P107_R17 = new ItemBuilder().withItemName(
                    "NF Q91 U132 B11 E84 P107 R17")
            .withItemQuantity("91")
            .withItemUnit("YUE")
            .withItemBoughtDate("11-04-2022")
            .withItemExpiryDate("28-06-2022")
            .withItemPrice("107")
            .withItemRemarks("DDT")
            .build();
    public static final Item NG_Q92_U117_B54_E105_P75_R49 = new ItemBuilder().withItemName(
                    "NG Q92 U117 B54 E105 P75 R49")
            .withItemQuantity("92")
            .withItemUnit("WCL")
            .withItemBoughtDate("26-05-2022")
            .withItemExpiryDate("21-07-2022")
            .withItemPrice("75")
            .withItemRemarks("IOI")
            .build();
    public static final Item NG_Q93_U121_B36_E17_P79_R90 = new ItemBuilder().withItemName("NG Q93 U121 B36 E17 P79 R90")
            .withItemQuantity("93")
            .withItemUnit("WVA")
            .withItemBoughtDate("08-05-2022")
            .withItemExpiryDate("17-04-2022")
            .withItemPrice("79")
            .withItemRemarks("RCU")
            .build();
    public static final Item NE_Q94_U57_B39_E29_P34_R34 = new ItemBuilder().withItemName("NE Q94 U57 B39 E29 P34 R34")
            .withItemQuantity("94")
            .withItemUnit("LAK")
            .withItemBoughtDate("11-05-2022")
            .withItemExpiryDate("01-05-2022")
            .withItemPrice("34")
            .withItemRemarks("GBM")
            .build();
    public static final Item NF_Q95_U97_B42_E74_P119_R13 = new ItemBuilder().withItemName("NF Q95 U97 B42 E74 P119 R13")
            .withItemQuantity("95")
            .withItemUnit("TOF")
            .withItemBoughtDate("14-05-2022")
            .withItemExpiryDate("18-06-2022")
            .withItemPrice("119")
            .withItemRemarks("BKU")
            .build();
    public static final Item NC_Q96_U49_B107_E71_P47_R32 = new ItemBuilder().withItemName("NC Q96 U49 B107 E71 P47 R32")
            .withItemQuantity("96")
            .withItemUnit("IWL")
            .withItemBoughtDate("23-07-2022")
            .withItemExpiryDate("15-06-2022")
            .withItemPrice("47")
            .withItemRemarks("FXN")
            .build();
    public static final Item NE_Q97_U118_B142_E93_P131_R99 = new ItemBuilder().withItemName(
                    "NE Q97 U118 B142 E93 P131 R99")
            .withItemQuantity("97")
            .withItemUnit("WNM")
            .withItemBoughtDate("02-09-2022")
            .withItemExpiryDate("09-07-2022")
            .withItemPrice("131")
            .withItemRemarks("SVW")
            .build();
    public static final Item NH_Q98_U70_B129_E53_P54_R105 = new ItemBuilder().withItemName(
                    "NH Q98 U70 B129 E53 P54 R105")
            .withItemQuantity("98")
            .withItemUnit("OZT")
            .withItemBoughtDate("17-08-2022")
            .withItemExpiryDate("25-05-2022")
            .withItemPrice("54")
            .withItemRemarks("TQT")
            .build();
    public static final Item NG_Q99_U130_B8_E69_P139_R112 = new ItemBuilder().withItemName(
                    "NG Q99 U130 B8 E69 P139 R112")
            .withItemQuantity("99")
            .withItemUnit("YPF")
            .withItemBoughtDate("08-04-2022")
            .withItemExpiryDate("13-06-2022")
            .withItemPrice("139")
            .withItemRemarks("UNE")
            .build();
    public static final Item NF_Q100_U88_B45_E6_P109_R106 = new ItemBuilder().withItemName(
                    "NF Q100 U88 B45 E6 P109 R106")
            .withItemQuantity("100")
            .withItemUnit("RVI")
            .withItemBoughtDate("17-05-2022")
            .withItemExpiryDate("06-04-2022")
            .withItemPrice("109")
            .withItemRemarks("TQW")
            .build();
    public static final Item NH_Q101_U35_B68_E131_P74_R125 = new ItemBuilder().withItemName(
                    "NH Q101 U35 B68 E131 P74 R125")
            .withItemQuantity("101")
            .withItemUnit("GDT")
            .withItemBoughtDate("12-06-2022")
            .withItemExpiryDate("19-08-2022")
            .withItemPrice("74")
            .withItemRemarks("WWV")
            .build();
    public static final Item NG_Q102_U28_B139_E94_P102_R26 = new ItemBuilder().withItemName(
                    "NG Q102 U28 B139 E94 P102 R26")
            .withItemQuantity("102")
            .withItemUnit("FIS")
            .withItemBoughtDate("27-08-2022")
            .withItemExpiryDate("10-07-2022")
            .withItemPrice("102")
            .withItemRemarks("EWA")
            .build();
    public static final Item NE_Q103_U48_B99_E143_P140_R144 = new ItemBuilder().withItemName(
                    "NE Q103 U48 B99 E143 P140 R144")
            .withItemQuantity("103")
            .withItemUnit("IIP")
            .withItemBoughtDate("15-07-2022")
            .withItemExpiryDate("03-09-2022")
            .withItemPrice("140")
            .withItemRemarks("ZWA")
            .build();
    public static final Item NC_Q104_U119_B59_E63_P32_R57 = new ItemBuilder().withItemName(
                    "NC Q104 U119 B59 E63 P32 R57")
            .withItemQuantity("104")
            .withItemUnit("WQT")
            .withItemBoughtDate("03-06-2022")
            .withItemExpiryDate("07-06-2022")
            .withItemPrice("32")
            .withItemRemarks("KCX")
            .build();
    public static final Item NL_Q105_U116_B127_E83_P69_R65 = new ItemBuilder().withItemName(
                    "NL Q105 U116 B127 E83 P69 R65")
            .withItemQuantity("105")
            .withItemUnit("VZT")
            .withItemBoughtDate("15-08-2022")
            .withItemExpiryDate("27-06-2022")
            .withItemPrice("69")
            .withItemRemarks("LVF")
            .build();
    public static final Item NB_Q106_U55_B66_E132_P9_R67 = new ItemBuilder().withItemName("NB Q106 U55 B66 E132 P9 R67")
            .withItemQuantity("106")
            .withItemUnit("KWF")
            .withItemBoughtDate("10-06-2022")
            .withItemExpiryDate("20-08-2022")
            .withItemPrice("9")
            .withItemRemarks("MBG")
            .build();
    public static final Item NK_Q107_U76_B1_E127_P63_R35 = new ItemBuilder().withItemName("NK Q107 U76 B1 E127 P63 R35")
            .withItemQuantity("107")
            .withItemUnit("PPZ")
            .withItemBoughtDate("01-04-2022")
            .withItemExpiryDate("15-08-2022")
            .withItemPrice("63")
            .withItemRemarks("GBP")
            .build();
    public static final Item NH_Q108_U33_B13_E43_P23_R70 = new ItemBuilder().withItemName("NH Q108 U33 B13 E43 P23 R70")
            .withItemQuantity("108")
            .withItemUnit("FVO")
            .withItemBoughtDate("13-04-2022")
            .withItemExpiryDate("15-05-2022")
            .withItemPrice("23")
            .withItemRemarks("MMY")
            .build();
    public static final Item NG_Q109_U39_B71_E50_P64_R9 = new ItemBuilder().withItemName("NG Q109 U39 B71 E50 P64 R9")
            .withItemQuantity("109")
            .withItemUnit("GQK")
            .withItemBoughtDate("15-06-2022")
            .withItemExpiryDate("22-05-2022")
            .withItemPrice("64")
            .withItemRemarks("BDT")
            .build();
    public static final Item NI_Q110_U111_B135_E25_P6_R89 = new ItemBuilder().withItemName(
                    "NI Q110 U111 B135 E25 P6 R89")
            .withItemQuantity("110")
            .withItemUnit("VOB")
            .withItemBoughtDate("23-08-2022")
            .withItemExpiryDate("25-04-2022")
            .withItemPrice("6")
            .withItemRemarks("RBN")
            .build();
    public static final Item NG_Q111_U92_B75_E54_P33_R118 = new ItemBuilder().withItemName(
                    "NG Q111 U92 B75 E54 P33 R118")
            .withItemQuantity("111")
            .withItemUnit("SPR")
            .withItemBoughtDate("19-06-2022")
            .withItemExpiryDate("26-05-2022")
            .withItemPrice("33")
            .withItemRemarks("VON")
            .build();
    public static final Item NC_Q112_U41_B49_E139_P2_R141 = new ItemBuilder().withItemName(
                    "NC Q112 U41 B49 E139 P2 R141")
            .withItemQuantity("112")
            .withItemUnit("HDM")
            .withItemBoughtDate("21-05-2022")
            .withItemExpiryDate("27-08-2022")
            .withItemPrice("2")
            .withItemRemarks("ZIB")
            .build();
    public static final Item NJ_Q113_U21_B46_E119_P120_R53 = new ItemBuilder().withItemName(
                    "NJ Q113 U21 B46 E119 P120 R53")
            .withItemQuantity("113")
            .withItemUnit("DRL")
            .withItemBoughtDate("18-05-2022")
            .withItemExpiryDate("07-08-2022")
            .withItemPrice("120")
            .withItemRemarks("IZV")
            .build();
    public static final Item NH_Q114_U46_B37_E121_P126_R110 = new ItemBuilder().withItemName(
                    "NH Q114 U46 B37 E121 P126 R110")
            .withItemQuantity("114")
            .withItemUnit("HWR")
            .withItemBoughtDate("09-05-2022")
            .withItemExpiryDate("09-08-2022")
            .withItemPrice("126")
            .withItemRemarks("UCE")
            .build();
    public static final Item NL_Q115_U102_B121_E115_P117_R42 = new ItemBuilder().withItemName(
                    "NL Q115 U102 B121 E115 P117 R42")
            .withItemQuantity("115")
            .withItemUnit("UIR")
            .withItemBoughtDate("09-08-2022")
            .withItemExpiryDate("03-08-2022")
            .withItemPrice("117")
            .withItemRemarks("HMU")
            .build();
    public static final Item ND_Q116_U86_B58_E10_P134_R27 = new ItemBuilder().withItemName(
                    "ND Q116 U86 B58 E10 P134 R27")
            .withItemQuantity("116")
            .withItemUnit("RMY")
            .withItemBoughtDate("02-06-2022")
            .withItemExpiryDate("10-04-2022")
            .withItemPrice("134")
            .withItemRemarks("EZE")
            .build();
    public static final Item NL_Q117_U129_B19_E79_P104_R126 = new ItemBuilder().withItemName(
                    "NL Q117 U129 B19 E79 P104 R126")
            .withItemQuantity("117")
            .withItemUnit("YEA")
            .withItemBoughtDate("19-04-2022")
            .withItemExpiryDate("23-06-2022")
            .withItemPrice("104")
            .withItemRemarks("XBR")
            .build();
    public static final Item NJ_Q118_U107_B115_E109_P18_R51 = new ItemBuilder().withItemName(
                    "NJ Q118 U107 B115 E109 P18 R51")
            .withItemQuantity("118")
            .withItemUnit("VEA")
            .withItemBoughtDate("03-08-2022")
            .withItemExpiryDate("25-07-2022")
            .withItemPrice("18")
            .withItemRemarks("IWK")
            .build();
    public static final Item NE_Q119_U56_B138_E27_P113_R129 = new ItemBuilder().withItemName(
                    "NE Q119 U56 B138 E27 P113 R129")
            .withItemQuantity("119")
            .withItemUnit("KWY")
            .withItemBoughtDate("26-08-2022")
            .withItemExpiryDate("27-04-2022")
            .withItemPrice("113")
            .withItemRemarks("XXS")
            .build();
    public static final Item NA_Q120_U9_B125_E144_P89_R143 = new ItemBuilder().withItemName(
                    "NA Q120 U9 B125 E144 P89 R143")
            .withItemQuantity("120")
            .withItemUnit("BCN")
            .withItemBoughtDate("13-08-2022")
            .withItemExpiryDate("04-09-2022")
            .withItemPrice("89")
            .withItemRemarks("ZNJ")
            .build();
    public static final Item ND_Q121_U26_B43_E36_P133_R2 = new ItemBuilder().withItemName("ND Q121 U26 B43 E36 P133 R2")
            .withItemQuantity("121")
            .withItemUnit("FBX")
            .withItemBoughtDate("15-05-2022")
            .withItemExpiryDate("08-05-2022")
            .withItemPrice("133")
            .withItemRemarks("ABE")
            .build();
    public static final Item NE_Q122_U61_B38_E99_P56_R92 = new ItemBuilder().withItemName("NE Q122 U61 B38 E99 P56 R92")
            .withItemQuantity("122")
            .withItemUnit("LVJ")
            .withItemBoughtDate("10-05-2022")
            .withItemExpiryDate("15-07-2022")
            .withItemPrice("56")
            .withItemRemarks("RKP")
            .build();
    public static final Item NJ_Q124_U83_B78_E100_P73_R71 = new ItemBuilder().withItemName(
                    "NJ Q124 U83 B78 E100 P73 R71")
            .withItemQuantity("124")
            .withItemUnit("RHE")
            .withItemBoughtDate("22-06-2022")
            .withItemExpiryDate("16-07-2022")
            .withItemPrice("73")
            .withItemRemarks("MNW")
            .build();
    public static final Item NF_Q123_U54_B73_E135_P91_R58 = new ItemBuilder().withItemName(
                    "NF Q123 U54 B73 E135 P91 R58")
            .withItemQuantity("123")
            .withItemUnit("KRV")
            .withItemBoughtDate("17-06-2022")
            .withItemExpiryDate("23-08-2022")
            .withItemPrice("91")
            .withItemRemarks("KQH")
            .build();
    public static final Item ND_Q125_U72_B100_E35_P138_R95 = new ItemBuilder().withItemName(
                    "ND Q125 U72 B100 E35 P138 R95")
            .withItemQuantity("125")
            .withItemUnit("PDO")
            .withItemBoughtDate("16-07-2022")
            .withItemExpiryDate("07-05-2022")
            .withItemPrice("138")
            .withItemRemarks("RVQ")
            .build();
    public static final Item NI_Q126_U106_B22_E51_P55_R120 = new ItemBuilder().withItemName(
                    "NI Q126 U106 B22 E51 P55 R120")
            .withItemQuantity("126")
            .withItemUnit("VDJ")
            .withItemBoughtDate("22-04-2022")
            .withItemExpiryDate("23-05-2022")
            .withItemPrice("55")
            .withItemRemarks("VYW")
            .build();
    public static final Item NC_Q127_U38_B18_E126_P90_R88 = new ItemBuilder().withItemName(
                    "NC Q127 U38 B18 E126 P90 R88")
            .withItemQuantity("127")
            .withItemUnit("GPN")
            .withItemBoughtDate("18-04-2022")
            .withItemExpiryDate("14-08-2022")
            .withItemPrice("90")
            .withItemRemarks("RAO")
            .build();
    public static final Item ND_Q128_U42_B124_E124_P84_R97 = new ItemBuilder().withItemName(
                    "ND Q128 U42 B124 E124 P84 R97")
            .withItemQuantity("128")
            .withItemUnit("HFL")
            .withItemBoughtDate("12-08-2022")
            .withItemExpiryDate("12-08-2022")
            .withItemPrice("84")
            .withItemRemarks("SLT")
            .build();
    public static final Item NI_Q129_U8_B64_E137_P87_R41 = new ItemBuilder().withItemName("NI Q129 U8 B64 E137 P87 R41")
            .withItemQuantity("129")
            .withItemUnit("AYP")
            .withItemBoughtDate("08-06-2022")
            .withItemExpiryDate("25-08-2022")
            .withItemPrice("87")
            .withItemRemarks("HCR")
            .build();
    public static final Item NA_Q130_U12_B21_E108_P144_R115 = new ItemBuilder().withItemName(
                    "NA Q130 U12 B21 E108 P144 R115")
            .withItemQuantity("130")
            .withItemUnit("BVC")
            .withItemBoughtDate("21-04-2022")
            .withItemExpiryDate("24-07-2022")
            .withItemPrice("144")
            .withItemRemarks("UPZ")
            .build();
    public static final Item ND_Q131_U25_B89_E30_P61_R75 = new ItemBuilder().withItemName("ND Q131 U25 B89 E30 P61 R75")
            .withItemQuantity("131")
            .withItemUnit("EMQ")
            .withItemBoughtDate("05-07-2022")
            .withItemExpiryDate("02-05-2022")
            .withItemPrice("61")
            .withItemRemarks("NNP")
            .build();
    public static final Item NA_Q132_U115_B91_E9_P76_R33 = new ItemBuilder().withItemName("NA Q132 U115 B91 E9 P76 R33")
            .withItemQuantity("132")
            .withItemUnit("VXZ")
            .withItemBoughtDate("07-07-2022")
            .withItemExpiryDate("09-04-2022")
            .withItemPrice("76")
            .withItemRemarks("GAG")
            .build();
    public static final Item NE_Q133_U22_B144_E46_P25_R22 = new ItemBuilder().withItemName(
                    "NE Q133 U22 B144 E46 P25 R22")
            .withItemQuantity("133")
            .withItemUnit("DXK")
            .withItemBoughtDate("04-09-2022")
            .withItemExpiryDate("18-05-2022")
            .withItemPrice("25")
            .withItemRemarks("ELV")
            .build();
    public static final Item NH_Q134_U137_B67_E38_P40_R10 = new ItemBuilder().withItemName(
                    "NH Q134 U137 B67 E38 P40 R10")
            .withItemQuantity("134")
            .withItemUnit("ZAU")
            .withItemBoughtDate("11-06-2022")
            .withItemExpiryDate("10-05-2022")
            .withItemPrice("40")
            .withItemRemarks("BEU")
            .build();
    public static final Item NL_Q135_U103_B140_E12_P15_R73 = new ItemBuilder().withItemName(
                    "NL Q135 U103 B140 E12 P15 R73")
            .withItemQuantity("135")
            .withItemUnit("UNP")
            .withItemBoughtDate("28-08-2022")
            .withItemExpiryDate("12-04-2022")
            .withItemPrice("15")
            .withItemRemarks("MWC")
            .build();
    public static final Item NI_Q136_U66_B24_E125_P121_R11 = new ItemBuilder().withItemName(
                    "NI Q136 U66 B24 E125 P121 R11")
            .withItemQuantity("136")
            .withItemUnit("NTP")
            .withItemBoughtDate("24-04-2022")
            .withItemExpiryDate("13-08-2022")
            .withItemPrice("121")
            .withItemRemarks("BFO")
            .build();
    public static final Item NA_Q137_U138_B20_E142_P80_R16 = new ItemBuilder().withItemName(
                    "NA Q137 U138 B20 E142 P80 R16")
            .withItemQuantity("137")
            .withItemUnit("ZDQ")
            .withItemBoughtDate("20-04-2022")
            .withItemExpiryDate("02-09-2022")
            .withItemPrice("80")
            .withItemRemarks("CHR")
            .build();
    public static final Item NG_Q138_U109_B80_E67_P36_R121 = new ItemBuilder().withItemName(
                    "NG Q138 U109 B80 E67 P36 R121")
            .withItemQuantity("138")
            .withItemUnit("VMJ")
            .withItemBoughtDate("24-06-2022")
            .withItemExpiryDate("11-06-2022")
            .withItemPrice("36")
            .withItemRemarks("WHF")
            .build();
    public static final Item NK_Q139_U3_B93_E26_P101_R45 = new ItemBuilder().withItemName("NK Q139 U3 B93 E26 P101 R45")
            .withItemQuantity("139")
            .withItemUnit("AMM")
            .withItemBoughtDate("09-07-2022")
            .withItemExpiryDate("26-04-2022")
            .withItemPrice("101")
            .withItemRemarks("HRI")
            .build();
    public static final Item NB_Q140_U95_B65_E106_P22_R83 = new ItemBuilder().withItemName(
                    "NB Q140 U95 B65 E106 P22 R83")
            .withItemQuantity("140")
            .withItemUnit("TJL")
            .withItemBoughtDate("09-06-2022")
            .withItemExpiryDate("22-07-2022")
            .withItemPrice("22")
            .withItemRemarks("PSE")
            .build();
    public static final Item NE_Q142_U142_B41_E68_P70_R38 = new ItemBuilder().withItemName(
                    "NE Q142 U142 B41 E68 P70 R38")
            .withItemQuantity("142")
            .withItemUnit("ZNJ")
            .withItemBoughtDate("13-05-2022")
            .withItemExpiryDate("12-06-2022")
            .withItemPrice("70")
            .withItemRemarks("GOK")
            .build();
    public static final Item NB_Q141_U134_B123_E33_P125_R69 = new ItemBuilder().withItemName(
                    "NB Q141 U134 B123 E33 P125 R69")
            .withItemQuantity("141")
            .withItemUnit("YXL")
            .withItemBoughtDate("11-08-2022")
            .withItemExpiryDate("05-05-2022")
            .withItemPrice("125")
            .withItemRemarks("MKO")
            .build();
    public static final Item NH_Q143_U78_B84_E136_P108_R102 = new ItemBuilder().withItemName(
                    "NH Q143 U78 B84 E136 P108 R102")
            .withItemQuantity("143")
            .withItemUnit("QER")
            .withItemBoughtDate("28-06-2022")
            .withItemExpiryDate("24-08-2022")
            .withItemPrice("108")
            .withItemRemarks("TDC")
            .build();
    public static final Item NK_Q144_U6_B105_E64_P51_R122 = new ItemBuilder().withItemName(
                    "NK Q144 U6 B105 E64 P51 R122")
            .withItemQuantity("144")
            .withItemUnit("AXP")
            .withItemBoughtDate("21-07-2022")
            .withItemExpiryDate("08-06-2022")
            .withItemPrice("51")
            .withItemRemarks("WMZ")
            .build();

    private ItemsToSort() {
    } // prevents instantiation

    public static List<Item> getSortItems() {
        return new ArrayList<>(Arrays.asList(NK_Q10_U32_B23_E23_P1_R84,
                                             NC_Q112_U41_B49_E139_P2_R141,
                                             ND_Q54_U17_B104_E88_P3_R29,
                                             NL_Q65_U36_B88_E133_P4_R136,
                                             NJ_Q88_U90_B143_E97_P5_R138,
                                             NI_Q110_U111_B135_E25_P6_R89,
                                             NC_Q9_U4_B28_E28_P7_R7,
                                             NK_Q21_U112_B118_E42_P8_R142,
                                             NB_Q106_U55_B66_E132_P9_R67,
                                             NB_Q85_U144_B56_E11_P10_R40,
                                             ND_Q89_U7_B74_E37_P11_R101,
                                             NJ_Q63_U18_B72_E52_P12_R66,
                                             NI_Q1_U114_B119_E18_P13_R3,
                                             NC_Q28_U29_B112_E73_P14_R130,
                                             NL_Q135_U103_B140_E12_P15_R73,
                                             NH_Q40_U96_B6_E140_P16_R68,
                                             NB_Q48_U51_B51_E116_P17_R100,
                                             NJ_Q118_U107_B115_E109_P18_R51,
                                             NA_Q76_U2_B98_E114_P19_R117,
                                             ND_Q26_U135_B83_E113_P20_R44,
                                             NJ_Q87_U122_B90_E134_P21_R119,
                                             NB_Q140_U95_B65_E106_P22_R83,
                                             NH_Q108_U33_B13_E43_P23_R70,
                                             NK_Q45_U143_B111_E20_P24_R60,
                                             NE_Q133_U22_B144_E46_P25_R22,
                                             NK_Q62_U47_B29_E62_P26_R23,
                                             NI_Q71_U50_B32_E80_P27_R80,
                                             NF_Q61_U34_B79_E129_P28_R107,
                                             NF_Q81_U141_B122_E58_P29_R79,
                                             NG_Q3_U62_B55_E31_P30_R48,
                                             NG_Q37_U108_B10_E8_P31_R127,
                                             NC_Q104_U119_B59_E63_P32_R57,
                                             NG_Q111_U92_B75_E54_P33_R118,
                                             NE_Q94_U57_B39_E29_P34_R34,
                                             NB_Q31_U20_B94_E138_P35_R123,
                                             NG_Q138_U109_B80_E67_P36_R121,
                                             NK_Q41_U131_B4_E95_P37_R1,
                                             NI_Q90_U126_B108_E3_P38_R52,
                                             NJ_Q86_U52_B92_E112_P39_R124,
                                             NH_Q134_U137_B67_E38_P40_R10,
                                             NK_Q12_U91_B62_E60_P41_R18,
                                             NF_Q74_U98_B48_E70_P42_R20,
                                             NK_Q72_U136_B95_E111_P43_R113,
                                             NI_Q35_U100_B114_E81_P44_R114,
                                             NJ_Q17_U37_B12_E103_P45_R31,
                                             NG_Q49_U133_B86_E87_P46_R19,
                                             NC_Q96_U49_B107_E71_P47_R32,
                                             NL_Q50_U84_B60_E13_P48_R21,
                                             NA_Q8_U14_B87_E57_P49_R14,
                                             NL_Q57_U75_B57_E101_P50_R30,
                                             NK_Q144_U6_B105_E64_P51_R122,
                                             NC_Q79_U58_B103_E14_P52_R61,
                                             NI_Q69_U127_B9_E72_P53_R77,
                                             NB_Q11_U94_B40_E78_P97_R104,
                                             NI_Q126_U106_B22_E51_P55_R120,
                                             NE_Q122_U61_B38_E99_P56_R92,
                                             NK_Q67_U139_B26_E15_P57_R37,
                                             NA_Q13_U59_B17_E39_P58_R24,
                                             NF_Q77_U123_B3_E4_P59_R25,
                                             NL_Q25_U5_B96_E89_P60_R103,
                                             ND_Q131_U25_B89_E30_P61_R75,
                                             NL_Q56_U105_B131_E22_P62_R6,
                                             NK_Q107_U76_B1_E127_P63_R35,
                                             NG_Q109_U39_B71_E50_P64_R9,
                                             NA_Q42_U67_B53_E40_P65_R140,
                                             NJ_Q7_U120_B25_E49_P66_R59,
                                             NB_Q64_U110_B31_E5_P67_R91,
                                             NA_Q30_U45_B128_E82_P68_R116,
                                             NL_Q105_U116_B127_E83_P69_R65,
                                             NE_Q142_U142_B41_E68_P70_R38,
                                             NI_Q52_U140_B77_E61_P71_R85,
                                             NH_Q84_U13_B52_E41_P72_R78,
                                             NJ_Q124_U83_B78_E100_P73_R71,
                                             NH_Q101_U35_B68_E131_P74_R125,
                                             NG_Q92_U117_B54_E105_P75_R49,
                                             NA_Q132_U115_B91_E9_P76_R33,
                                             NC_Q75_U30_B2_E86_P77_R47,
                                             NJ_Q59_U128_B137_E102_P78_R64,
                                             NG_Q93_U121_B36_E17_P79_R90,
                                             NA_Q137_U138_B20_E142_P80_R16,
                                             NC_Q19_U69_B109_E66_P81_R50,
                                             NL_Q73_U23_B5_E32_P82_R8,
                                             NE_Q66_U27_B132_E117_P83_R54,
                                             ND_Q128_U42_B124_E124_P84_R97,
                                             NG_Q55_U82_B141_E19_P85_R128,
                                             NC_Q60_U85_B7_E118_P86_R132,
                                             NI_Q129_U8_B64_E137_P87_R41,
                                             NJ_Q39_U68_B14_E34_P88_R96,
                                             NA_Q120_U9_B125_E144_P89_R143,
                                             NC_Q127_U38_B18_E126_P90_R88,
                                             NF_Q123_U54_B73_E135_P91_R58,
                                             NE_Q22_U89_B133_E110_P92_R15,
                                             NF_Q68_U31_B101_E2_P93_R131,
                                             NA_Q24_U104_B130_E90_P94_R74,
                                             NE_Q53_U64_B44_E45_P95_R46,
                                             NG_Q2_U124_B63_E65_P96_R133,
                                             NH_Q98_U70_B129_E53_P54_R105,
                                             NA_Q18_U11_B47_E92_P98_R108,
                                             ND_Q46_U74_B97_E48_P99_R12,
                                             NH_Q44_U43_B69_E47_P100_R109,
                                             NK_Q139_U3_B93_E26_P101_R45,
                                             NG_Q102_U28_B139_E94_P102_R26,
                                             ND_Q15_U53_B120_E55_P103_R135,
                                             NL_Q117_U129_B19_E79_P104_R126,
                                             NB_Q51_U10_B30_E1_P105_R94,
                                             NC_Q78_U71_B85_E56_P106_R134,
                                             NF_Q91_U132_B11_E84_P107_R17,
                                             NH_Q143_U78_B84_E136_P108_R102,
                                             NF_Q100_U88_B45_E6_P109_R106,
                                             NH_Q36_U73_B81_E16_P110_R86,
                                             NF_Q32_U113_B35_E96_P111_R98,
                                             NL_Q58_U101_B50_E75_P112_R5,
                                             NE_Q119_U56_B138_E27_P113_R129,
                                             NI_Q4_U87_B117_E107_P114_R56,
                                             NB_Q27_U40_B113_E85_P115_R87,
                                             NF_Q23_U1_B82_E59_P116_R139,
                                             NL_Q115_U102_B121_E115_P117_R42,
                                             NK_Q20_U60_B110_E24_P118_R81,
                                             NF_Q95_U97_B42_E74_P119_R13,
                                             NJ_Q113_U21_B46_E119_P120_R53,
                                             NI_Q136_U66_B24_E125_P121_R11,
                                             NL_Q47_U81_B76_E77_P122_R93,
                                             NI_Q16_U77_B61_E130_P123_R137,
                                             NE_Q38_U19_B70_E7_P124_R36,
                                             NB_Q141_U134_B123_E33_P125_R69,
                                             NH_Q114_U46_B37_E121_P126_R110,
                                             ND_Q33_U15_B116_E91_P127_R39,
                                             NA_Q5_U93_B134_E122_P128_R55,
                                             NF_Q82_U125_B34_E76_P129_R28,
                                             NB_Q83_U63_B33_E104_P130_R62,
                                             NE_Q97_U118_B142_E93_P131_R99,
                                             ND_Q14_U44_B136_E128_P132_R43,
                                             ND_Q121_U26_B43_E36_P133_R2,
                                             ND_Q116_U86_B58_E10_P134_R27,
                                             NC_Q29_U79_B126_E123_P135_R63,
                                             NB_Q34_U16_B27_E98_P136_R111,
                                             NH_Q6_U80_B106_E44_P137_R4,
                                             ND_Q125_U72_B100_E35_P138_R95,
                                             NG_Q99_U130_B8_E69_P139_R112,
                                             NE_Q103_U48_B99_E143_P140_R144,
                                             NH_Q43_U65_B15_E141_P141_R72,
                                             NE_Q80_U99_B16_E120_P142_R82,
                                             NJ_Q70_U24_B102_E21_P143_R76,
                                             NA_Q130_U12_B21_E108_P144_R115));
    }
}
