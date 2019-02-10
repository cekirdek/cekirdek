package yardımcı;

public class Değişkenler {

	public static final String UYGULAMA_ADI_VE_SÜRÜMÜ = "Çekirdek sürüm 0.0.4";
	
	public static boolean DİL_TÜRKÇE_Mİ = "tr".equals(System.getProperty("user.language"));
	
	public enum SÖZCÜK {
		TİP_00YOK,
		TİP_01İSİM,
		TİP_02OPERATÖR,
		TİP_03TAM_SAYI,
		TİP_04ONDALIKLI_SAYI,
		TİP_05METİN,
		TİP_06SATIR_SONU,
		TİP_07DEĞİŞKEN_TİPİ,
		TİP_08NOKTA,
		TİP_09ATAMA_SOLA,
		TİP_10ATAMA_SAĞA,
		TİP_11TANIMLAMA_SOLA,
		TİP_12TANIMLAMA_SAĞA,
		TİP_13ÖZELLİK,
		TİP_14AÇ_PARANTEZ,
		TİP_15KAPA_PARANTEZ,
		TİP_16AÇ_SÜSLÜ,
		TİP_17KAPA_SÜSLÜ,
		TİP_18VİRGÜL
	}
	
	public enum CÜMLE {
		TİP_01DEĞİŞKEN_YENİ,
		TİP_02GEÇİCİ_DEĞİŞKEN_YENİ,
		TİP_03DEĞİŞKEN_SİL,
		TİP_04OPERATÖR_İŞLEMİ,
		TİP_05FUNKSİYON_ÇAĞRISI,
		TİP_06DEĞİŞKEN_ATAMA,
		TİP_07MAKİNE_DİLİ_KOD,
		TİP_08MAKİNE_DİLİ_VERİ,
		TİP_09MAKİNE_DİLİ_SABİT_VERİ,
		TİP_10SABİT_ATAMA
	}
	
	public enum FONKSİYON {
		TİP_01OPERATÖRFONKSİYON,
		TİP_02İSİMLİFONKSİYON,
		TİP_03ANAFONKSİYON
	}
	
	public enum SAKLAÇ {
		RAX, RBX, RCX, RDX, RBP, RSI, RDI, RSP, R8, R9, R10, R11, R12, R13, R14, R15
	}

}
