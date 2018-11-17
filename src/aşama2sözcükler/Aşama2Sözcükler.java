package aşama2sözcükler;

import java.util.ArrayList;

import yardımcı.Değişkenler.SÖZCÜK;

public class Aşama2Sözcükler {

	private static Sözcük öncekiSözcük = null;
	private static ArrayList<Sözcük> aktifCümle = null;
	private static ArrayList<Sözcük> tümCümleler = null;

	public static void sözcükEkle(Sözcük sözcük) throws Exception {
		if (sözcük.tip == SÖZCÜK.TİP_06SATIR_SONU) {
			if (aktifCümle.size() > 0) {
				aktifCümle.add(sözcük);
				tümCümleler.addAll(aktifCümle);
				aktifCümle = new ArrayList<Sözcük>();
			} else {
				throw new Exception("Gereksiz yere ';' kullanılmış");
			}
		} else {
			if (öncekiSözcük.tip == SÖZCÜK.TİP_07DEĞİŞKEN_TİPİ && sözcük.tip == SÖZCÜK.TİP_10ATAMA_SAĞA) {
				aktifCümle.remove(aktifCümle.size() - 1);
				sözcük = new Sözcük(SÖZCÜK.TİP_12TANIMLAMA_SAĞA);
			} else if (öncekiSözcük.tip == SÖZCÜK.TİP_09ATAMA_SOLA && sözcük.tip == SÖZCÜK.TİP_07DEĞİŞKEN_TİPİ) {
				aktifCümle.remove(aktifCümle.size() - 1);
				sözcük = new Sözcük(SÖZCÜK.TİP_11TANIMLAMA_SOLA);
			}
			aktifCümle.add(sözcük);
		}
		öncekiSözcük = sözcük;
	}

	public static ArrayList<Sözcük> işle(String içerik) throws Exception {

		öncekiSözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
		aktifCümle = new ArrayList<Sözcük>();
		tümCümleler = new ArrayList<Sözcük>();

		// durum 0 : Normal durum
		// durum 1 : Operatör
		// durum 2 : Metin
		// durum 3 : isim
		// durum 4,5,6 : Tam sayı ve ondalıklı sayı
		// durum 7,8,9,10 : Açıklamalar
		int durum = 0;
		Sözcük sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);

		char[] karakterler = içerik.toCharArray();
		for (int i = 0; i < karakterler.length; i++) {
			char karakter = karakterler[i];
			//
			if (durum == 0) {
				// durum 0 : Normal durum
				if (karakter == '\r') {
					;
				} else if (karakter == '\'') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					durum = 7;
				} else if (karakter == ' ' || karakter == '\t' || karakter == '\n') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
				} else if (karakter == ';') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					sözcükEkle(new Sözcük(SÖZCÜK.TİP_06SATIR_SONU));
				} else if (karakter == ':') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					sözcükEkle(new Sözcük(SÖZCÜK.TİP_07DEĞİŞKEN_TİPİ));
				} else if (karakter == '.') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					sözcükEkle(new Sözcük(SÖZCÜK.TİP_08NOKTA));
				} else if (karakter == '<') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					sözcükEkle(new Sözcük(SÖZCÜK.TİP_09ATAMA_SOLA));
				} else if (karakter == '>') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
						sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					}
					sözcükEkle(new Sözcük(SÖZCÜK.TİP_10ATAMA_SAĞA));
				} else if (karakter == '"') {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
					}
					sözcük = new Sözcük_05Metin();
					durum = 2;
				} else {
					if (sözcük.tip != SÖZCÜK.TİP_00YOK) {
						sözcükEkle(sözcük);
					}
					if (Character.isDigit(karakter)) {
						durum = 4;
						sözcük = new Sözcük_03TamSayı(karakter);
					} else if (karakter == '_' || Character.isLetter(karakter)) {
						durum = 3;
						sözcük = new Sözcük_01İsim(karakter);
					} else if (karakter == '+' || karakter == '-' || karakter == '*' || karakter == '/') {
						durum = 1;
						sözcük = new Sözcük_02Operatör(karakter);
					} else {
						throw new Exception("Bilinmeyen Karakter : '" + karakter + "'");
					}
				}
			} else if (durum == 1) {
				// durum 1 : Operatör
				if (karakter == '+' || karakter == '-' || karakter == '*' || karakter == '/' || karakter == '<'
						|| karakter == '>') {
					((Sözcük_02Operatör) sözcük).operatör += karakter;
				} else {
					i--;
					durum = 0;
					sözcükEkle(sözcük);
					sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
				}
			} else if (durum == 2) {
				// durum 2 : Metin
				if (karakter == '"') {
					sözcükEkle(sözcük);
					sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
					durum = 0;
				} else {
					((Sözcük_05Metin) sözcük).metin += karakter;
				}
			} else if (durum == 3) {
				// durum 3 : isim
				if (karakter == '_' || Character.isLetter(karakter) || Character.isDigit(karakter)) {
					((Sözcük_01İsim) sözcük).isim += karakter;
				} else {
					i--;
					durum = 0;
					sözcükEkle(sözcük);
					sözcük = new Sözcük(SÖZCÜK.TİP_00YOK);
				}
			} else if (durum == 4) {
				// durum 4,5,6 : Tam sayı ve ondalıklı sayı
				if (Character.isDigit(karakter)) {
					((Sözcük_03TamSayı) sözcük).sayı += karakter;
				} else if (karakter == '.') {
					durum = 5;
					sözcük = new Sözcük_04OndalıklıSayı(((Sözcük_03TamSayı) sözcük).sayı);
					((Sözcük_04OndalıklıSayı) sözcük).sayı += karakter;
				} else {
					i--;
					durum = 0;
				}
			} else if (durum == 5) {
				// durum 4,5,6 : Tam sayı ve ondalıklı sayı
				if (Character.isDigit(karakter)) {
					durum = 6;
					((Sözcük_04OndalıklıSayı) sözcük).sayı += karakter;
				} else {
					i -= 2;
					sözcük = new Sözcük_03TamSayı(((Sözcük_04OndalıklıSayı) sözcük).sayı.substring(0,
							((Sözcük_04OndalıklıSayı) sözcük).sayı.length() - 1));
					durum = 0;
				}
			} else if (durum == 6) {
				// durum 4,5,6 : Tam sayı ve ondalıklı sayı
				if (Character.isDigit(karakter)) {
					((Sözcük_04OndalıklıSayı) sözcük).sayı += karakter;
				} else {
					i--;
					durum = 0;
				}
			} else if (durum == 7) {
				// durum 7,8,9,10 : Açıklamalar
				if (karakter == '\n') {
					i--;
					durum = 0;
				} else if (karakter == '\'') {
					durum = 9;
				} else {
					durum = 8;
				}
			} else if (durum == 8) {
				// durum 7,8,9,10 : Açıklamalar
				if (karakter == '\n') {
					i--;
					durum = 0;
				}
			} else if (durum == 9) {
				// durum 7,8,9,10 : Açıklamalar
				if (karakter == '\'') {
					durum = 10;
				}
			} else if (durum == 10) {
				// durum 7,8,9,10 : Açıklamalar
				if (karakter == '\'') {
					durum = 0;
				} else {
					durum = 9;
				}
			}
		}

		if (durum != 0) {
			throw new Exception("Anormal Sonlanma : Durum : " + durum);
		}

		return tümCümleler;
	}

}
