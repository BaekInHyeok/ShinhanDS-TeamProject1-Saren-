package com.team4.shoppingmall.category;

import java.util.Arrays;
import java.util.List;

public enum CategoryEnum {
	highest("highest", null, Arrays.asList("woman", "man", "kids", "luxury", "sports", "bagAndShoes")),

	woman("woman", "����",
			Arrays.asList("woman_outer", "woman_jacketAndVest", "woman_neat", "woman_shirtAndBlouse", "woman_tshirt",
					"woman_onepiece", "woman_pants", "woman_skirt", "woman_jewelryAndWatch")),

	woman_outer("woman_outer", "�ƿ���",
			Arrays.asList("woman_outer_coat", "woman_outer_jumper", "woman_outer_padding", "woman_outer_fur")),

	woman_outer_coat("woman_outer_coat", "��Ʈ",
			Arrays.asList("woman_outer_coat_coat", "woman_outer_coat_trench", "woman_outer_coat_fur")),

	woman_outer_coat_coat("woman_outer_coat_coat", "��Ʈ_9", null),

	woman_outer_coat_trench("woman_outer_coat_trench", "Ʈ��ġ_9", null),

	woman_outer_coat_fur("woman_outer_coat_fur", "��_9", null),

	woman_outer_jumper("woman_outer_jumper", "����",
			Arrays.asList("woman_outer_jumper_jumper", "woman_outer_jumper_safari", "woman_outer_jumper_windbreak")),

	woman_outer_jumper_jumper("woman_outer_jumper_jumper", "����_4", null),

	woman_outer_jumper_safari("woman_outer_jumper_safari", "�߻�/���ĸ�_4", null),

	woman_outer_jumper_windbreak("woman_outer_jumper_windbreak", "�ٶ�����_4", null),

	woman_outer_padding("woman_outer_padding", "�ٿ�/�е�_4", null),

	woman_outer_fur("woman_outer_fur", "��_4", null),

	woman_jacketAndVest("woman_jacketAndVest", "��Ŷ/����Ʈ",
			Arrays.asList("woman_jacketAndVest_jacket", "woman_jacketAndVest_blazer",
					"woman_jacketAndVest_leatherJacket", "woman_jacketAndVest_vest")),

	woman_jacketAndVest_jacket("woman_jacketAndVest_jacket", "��Ŷ_4", null),

	woman_jacketAndVest_blazer("woman_jacketAndVest_blazer", "������_4", null),

	woman_jacketAndVest_leatherJacket("woman_jacketAndVest_leatherJacket", "���� ��Ŷ_4", null),

	woman_jacketAndVest_vest("woman_jacketAndVest_vest", "����Ʈ_5", null),

	woman_neat("woman_neat", "��Ʈ", Arrays.asList("woman_neat_pullover", "woman_neat_cardigan", "woman_neat_vest")),

	woman_neat_pullover("woman_neat_pullover", "Ǯ����_2", null),

	woman_neat_cardigan("woman_neat_cardigan", "ī���_3", null),

	woman_neat_vest("woman_neat_vest", "����Ʈ_5", null),

	woman_shirtAndBlouse("woman_shirtAndBlouse", "����/���콺",
			Arrays.asList("woman_shirtAndBlouse_shirt", "woman_shirtAndBlouse_blouse")),

	woman_shirtAndBlouse_shirt("woman_shirtAndBlouse_shirt", "����",
			Arrays.asList("woman_shirtAndBlouse_shirt_sleevelong", "woman_shirtAndBlouse_shirt_sleeveshort")),

	woman_shirtAndBlouse_shirt_sleevelong("woman_shirtAndBlouse_shirt_sleevelong", "����_0", null),

	woman_shirtAndBlouse_shirt_sleeveshort("woman_shirtAndBlouse_shirt_sleeveshort", "����_0", null),

	woman_shirtAndBlouse_blouse("woman_shirtAndBlouse_blouse", "���콺",
			Arrays.asList("woman_shirtAndBlouse_blouse_sleevelong", "woman_shirtAndBlouse_blouse_sleeveshort",
					"woman_shirtAndBlouse_blouse_sleeveless")),

	woman_shirtAndBlouse_blouse_sleevelong("woman_shirtAndBlouse_blouse_sleevelong", "����_0", null),

	woman_shirtAndBlouse_blouse_sleeveshort("woman_shirtAndBlouse_blouse_sleeveshort", "����_0", null),

	woman_shirtAndBlouse_blouse_sleeveless("woman_shirtAndBlouse_blouse_sleeveless", "�μҸ�_0", null),

	woman_tshirt("woman_tshirt", "Ƽ����",
			Arrays.asList("woman_tshirt_sleevelong", "woman_tshirt_sleeveshort", "woman_tshirt_sleeveless")),

	woman_tshirt_sleevelong("woman_tshirt_sleevelong", "����_1", null),

	woman_tshirt_sleeveshort("woman_tshirt_sleeveshort", "����_1", null),

	woman_tshirt_sleeveless("woman_tshirt_sleeveless", "�μҸ�_1", null),

	woman_onepiece("woman_onepiece", "���ǽ�", Arrays.asList("woman_onepiece_sleevelong", "woman_onepiece_sleeveshort")),

	woman_onepiece_sleevelong("woman_onepiece_sleevelong", "����_10", null),

	woman_onepiece_sleeveshort("woman_onepiece_sleeveshort", "����/�μҸ�_10", null),

	woman_pants("woman_pants", "����",
			Arrays.asList("woman_pants_wide", "woman_pants_straight", "woman_pants_slim", "woman_pants_jogger",
					"woman_pants_denim", "woman_pants_short", "woman_pants_jumpsuit", "woman_pants_sport")),

	woman_pants_wide("woman_pants_wide", "���̵�_6", null),

	woman_pants_straight("woman_pants_straight", "��Ʈ����Ʈ_6", null),

	woman_pants_slim("woman_pants_slim", "����_6", null),

	woman_pants_jogger("woman_pants_jogger", "����_6", null),

	woman_pants_denim("woman_pants_denim", "����", Arrays.asList("woman_pants_denim_long", "woman_pants_denim_short")),

	woman_pants_denim_long("woman_pants_denim_long", "��/�̵�_6", null),

	woman_pants_denim_short("woman_pants_denim_short", "��Ʈ_7", null),

	woman_pants_short("woman_pants_short", "����/����_7", null),

	woman_pants_jumpsuit("woman_pants_jumpsuit", "������Ʈ_11", null),

	woman_pants_sport("woman_pants_sport", "������", Arrays.asList("woman_pants_sport_long", "woman_pants_sport_short")),

	woman_pants_sport_long("woman_pants_sport_long", "��/�̵� ����_6", null),

	woman_pants_sport_short("woman_pants_sport_short", "��Ʈ ����_7", null),

	woman_skirt("woman_skirt", "��ĿƮ", Arrays.asList("woman_skirt_long", "woman_skirt_short")),

	woman_skirt_long("woman_skirt_long", "��/�̵�_8", null),

	woman_skirt_short("woman_skirt_short", "�̴�_8", null),

	woman_jewelryAndWatch("woman_jewelryAndWatch", "���/�ð�",
			Arrays.asList("woman_jewelryAndWatch_jewerly", "woman_jewelryAndWatch_earing",
					"woman_jewelryAndWatch_necklace", "woman_jewelryAndWatch_bracelet", "woman_jewelryAndWatch_ring",
					"woman_jewelryAndWatch_hairAccessory", "woman_jewelryAndWatch_brooch",
					"woman_jewelryAndWatch_watch")),

	woman_jewelryAndWatch_jewerly("woman_jewelryAndWatch_jewerly", "���", null),

	woman_jewelryAndWatch_earing("woman_jewelryAndWatch_earing", "�Ͱ���", null),

	woman_jewelryAndWatch_necklace("woman_jewelryAndWatch_necklace", "�����", null),

	woman_jewelryAndWatch_bracelet("woman_jewelryAndWatch_bracelet", "����", null),

	woman_jewelryAndWatch_ring("woman_jewelryAndWatch_ring", "����", null),

	woman_jewelryAndWatch_hairAccessory("woman_jewelryAndWatch_hairAccessory", "���Ǽ�����", null),

	woman_jewelryAndWatch_brooch("woman_jewelryAndWatch_brooch", "�����/���Ʈ", null),

	woman_jewelryAndWatch_watch("woman_jewelryAndWatch_watch", "�ð�", null),

	man("man", "����",
			Arrays.asList("man_outer", "man_suit", "man_pants", "man_jacket", "man_shirt", "man_neat", "man_tshirt")),

	man_outer("man_outer", "�ƿ���",
			Arrays.asList("man_outer_jumper", "man_outer_coat", "man_outer_padding", "man_outer_leatherJacket")),

	man_outer_jumper("man_outer_jumper", "����",
			Arrays.asList("man_outer_jumper_jumper", "man_outer_jumper_safari", "man_outer_jumper_windbreak")),

	man_outer_jumper_jumper("man_outer_jumper_jumper", "����_4", null),

	man_outer_jumper_safari("man_outer_jumper_safari", "�߻�/���ĸ�_4", null),

	man_outer_jumper_windbreak("man_outer_jumper_windbreak", "�ٶ�����_4", null),

	man_outer_coat("man_outer_coat", "��Ʈ", Arrays.asList("man_outer_coat_coat", "man_outer_coat_trench")),

	man_outer_coat_coat("man_outer_coat_coat", "��Ʈ_9", null),

	man_outer_coat_trench("man_outer_coat_trench", "Ʈ��ġ_9", null),

	man_outer_padding("man_outer_padding", "�ٿ�/�е�_4", null),

	man_outer_leatherJacket("man_outer_leatherJacket", "������Ŷ_4", null),

	man_suit("man_suit", "����",
			Arrays.asList("man_suit_suitSet", "man_suit_suitJacket", "man_suit_suitPants", "man_suit_dressShirt",
					"man_suit_suitVest", "man_suit_setupSet")),

	man_suit_suitSet("man_suit_suitSet", "���� ��Ʈ", null),

	man_suit_suitJacket("man_suit_suitJacket", "���� ��Ŷ_4", null),

	man_suit_suitPants("man_suit_suitPants", "���� ����_6", null),

	man_suit_dressShirt("man_suit_dressShirt", "�巹�� ����_0", null),

	man_suit_suitVest("man_suit_suitVest", "���� ����Ʈ_5", null),

	man_suit_setupSet("man_suit_setupSet", "�¾� ��Ʈ", null),

	man_pants("man_pants", "����",
			Arrays.asList("man_pants_chinos", "man_pants_slacks", "man_pants_suitPants", "man_pants_jogger",
					"man_pants_denim", "man_pants_shorts")),

	man_pants_chinos("man_pants_chinos", "ġ��_6", null),

	man_pants_slacks("man_pants_slacks", "������_6", null),

	man_pants_suitPants("man_pants_suitPants", "��Ʈ����_6", null),

	man_pants_jogger("man_pants_jogger", "����/����_6", null),

	man_pants_denim("man_pants_denim", "����_6", null),

	man_pants_shorts("man_pants_shorts", "����_7", null),

	man_jacket("man_jacket", "��Ŷ/����Ʈ",
			Arrays.asList("man_jacket_jacket", "man_jacket_blazer", "man_jacket_leatherJacket", "man_jacket_vest")),

	man_jacket_jacket("man_jacket_jacket", "��Ŷ_4", null),

	man_jacket_blazer("man_jacket_blazer", "������_4", null),

	man_jacket_leatherJacket("man_jacket_leatherJacket", "���� ��Ŷ_4", null),

	man_jacket_vest("man_jacket_vest", "����Ʈ_5", null),

	man_shirt("man_shirt", "����", Arrays.asList("man_shirt_sleevelong", "man_shirt_sleeveshort")),

	man_shirt_sleevelong("man_shirt_sleevelong", "���ȼ���_0", null),

	man_shirt_sleeveshort("man_shirt_sleeveshort", "���ȼ���_0", null),

	man_neat("man_neat", "��Ʈ", Arrays.asList("man_neat_cardigan", "man_neat_pullover", "man_neat_vest")),

	man_neat_cardigan("man_neat_cardigan", "ī���_3", null),

	man_neat_pullover("man_neat_pullover", "Ǯ����_2", null),

	man_neat_vest("man_neat_vest", "����Ʈ_5", null),

	man_tshirt("man_tshirt", "Ƽ����",
			Arrays.asList("man_tshirt_sleeveshort", "man_tshirt_sleevelong", "man_tshirt_sleeveless")),

	man_tshirt_sleeveshort("man_tshirt_sleeveshort", "����Ƽ����_1", null),

	man_tshirt_sleevelong("man_tshirt_sleevelong", "����Ƽ����_1", null),

	man_tshirt_sleeveless("man_tshirt_sleeveless", "�μҸ�_1", null),

	kids("kids", "Ű��", Arrays.asList("kids_boy", "kids_girl")),

	kids_boy("kids_boy", "����",
			Arrays.asList("kids_boy_outer", "kids_boy_tshirt", "kids_boy_shirt", "kids_boy_neat", "kids_boy_pants")),

	kids_boy_outer("kids_boy_outer", "�ƿ���",
			Arrays.asList("kids_boy_outer_padding", "kids_boy_outer_jumper", "kids_boy_outer_coat",
					"kids_boy_outer_jacket")),

	kids_boy_outer_padding("kids_boy_outer_padding", "�ٿ�/�е�_4", null),

	kids_boy_outer_jumper("kids_boy_outer_jumper", "����_4", null),

	kids_boy_outer_coat("kids_boy_outer_coat", "��Ʈ_9", null),

	kids_boy_outer_jacket("kids_boy_outer_jacket", "��Ŷ/����Ʈ_5", null),

	kids_boy_tshirt("kids_boy_tshirt", "Ƽ����",
			Arrays.asList("kids_boy_tshirt_sleevelong", "kids_boy_tshirt_sleeveshort")),

	kids_boy_tshirt_sleevelong("kids_boy_tshirt_sleevelong", "����_1", null),

	kids_boy_tshirt_sleeveshort("kids_boy_tshirt_sleeveshort", "����/�μҸ�_1", null),

	kids_boy_shirt("kids_boy_shirt", "����", Arrays.asList("kids_boy_shirt_sleevelong", "kids_boy_shirt_sleeveshort")),

	kids_boy_shirt_sleevelong("kids_boy_shirt_sleevelong", "����_0", null),

	kids_boy_shirt_sleeveshort("kids_boy_shirt_sleeveshort", "����_0", null),

	kids_boy_neat("kids_boy_neat", "��Ʈ", Arrays.asList("kids_boy_neat_pullover", "kids_boy_neat_cardigan")),

	kids_boy_neat_pullover("kids_boy_neat_pullover", "Ǯ����_2", null),

	kids_boy_neat_cardigan("kids_boy_neat_cardigan", "ī���/����Ʈ_3", null),

	kids_boy_pants("kids_boy_pants", "����", Arrays.asList("kids_boy_pants_long", "kids_boy_pants_short")),

	kids_boy_pants_long("kids_boy_pants_long", "�� ����_6", null),

	kids_boy_pants_short("kids_boy_pants_short", "��Ʈ ����_7", null),

	kids_girl("kids_girl", "����",
			Arrays.asList("kids_girl_outer", "kids_girl_tshirt", "kids_girl_shirtAndBlouse", "kids_girl_onepiece",
					"kids_girl_neat", "kids_girl_pantsAndSkirt")),

	kids_girl_outer("kids_girl_outer", "�ƿ���",
			Arrays.asList("kids_girl_outer_padding", "kids_girl_outer_jumper", "kids_girl_outer_coat",
					"kids_girl_outer_jacket")),

	kids_girl_outer_padding("kids_girl_outer_padding", "�ٿ�/�е�_4", null),

	kids_girl_outer_jumper("kids_girl_outer_jumper", "����_4", null),

	kids_girl_outer_coat("kids_girl_outer_coat", "��Ʈ_9", null),

	kids_girl_outer_jacket("kids_girl_outer_jacket", "��Ŷ/����Ʈ_4", null),

	kids_girl_tshirt("kids_girl_tshirt", "Ƽ����",
			Arrays.asList("kids_girl_tshirt_sleevelong", "kids_girl_tshirt_sleeveshort")),

	kids_girl_tshirt_sleevelong("kids_girl_tshirt_sleevelong", "����_1", null),

	kids_girl_tshirt_sleeveshort("kids_girl_tshirt_sleeveshort", "����/�μҸ�_1", null),

	kids_girl_shirtAndBlouse("kids_girl_shirtAndBlouse", "����/���콺",
			Arrays.asList("kids_girl_shirtAndBlouse_sleevelong", "kids_girl_shirtAndBlouse_sleeveshort")),

	kids_girl_shirtAndBlouse_sleevelong("kids_girl_shirtAndBlouse_sleevelong", "����_0", null),

	kids_girl_shirtAndBlouse_sleeveshort("kids_girl_shirtAndBlouse_sleeveshort", "����_0", null),

	kids_girl_onepiece("kids_girl_onepiece", "���ǽ�_10", null),

	kids_girl_neat("kids_girl_neat", "��Ʈ", Arrays.asList("kids_girl_neat_pullover", "kids_girl_neat_cardigan")),

	kids_girl_neat_pullover("kids_girl_neat_pullover", "Ǯ����_2", null),

	kids_girl_neat_cardigan("kids_girl_neat_cardigan", "ī���/����Ʈ_3", null),

	kids_girl_pantsAndSkirt("kids_girl_pantsAndSkirt", "����/��ĿƮ",
			Arrays.asList("kids_girl_pantsAndSkirt_long", "kids_girl_pantsAndSkirt_skirt",
					"kids_girl_pantsAndSkirt_short")),

	kids_girl_pantsAndSkirt_long("kids_girl_pantsAndSkirt_long", "�� ����_6", null),

	kids_girl_pantsAndSkirt_skirt("kids_girl_pantsAndSkirt_skirt", "��ĿƮ_8", null),

	kids_girl_pantsAndSkirt_short("kids_girl_pantsAndSkirt_short", "��Ʈ ����_7", null),

	luxury("luxury", "���Ÿ�",
			Arrays.asList("luxury_woman", "luxury_womanBag", "luxury_womanShoes", "luxury_womanWatch", "luxury_man",
					"luxury_manBag", "luxury_manShoes", "luxury_manWatch")),

	luxury_woman("luxury_woman", "�����Ƿ�",
			Arrays.asList("luxury_woman_outer", "luxury_woman_jacketAndVest", "luxury_woman_tshirt",
					"luxury_woman_shirtAndBlouse", "luxury_woman_neat", "luxury_woman_onepiece", "luxury_woman_pants",
					"luxury_woman_skirt", "luxury_woman_beachwear")),

	luxury_woman_outer("luxury_woman_outer", "�ƿ���",
			Arrays.asList("luxury_woman_outer_coat", "luxury_woman_outer_jumper", "luxury_woman_outer_padding",
					"luxury_woman_outer_fur", "luxury_woman_outer_trench")),

	luxury_woman_outer_coat("luxury_woman_outer_coat", "��Ʈ_9", null),

	luxury_woman_outer_jumper("luxury_woman_outer_jumper", "����_4", null),

	luxury_woman_outer_padding("luxury_woman_outer_padding", "�ٿ�/�е�_4", null),

	luxury_woman_outer_fur("luxury_woman_outer_fur", "��_4", null),

	luxury_woman_outer_trench("luxury_woman_outer_trench", "Ʈ��ġ_9", null),

	luxury_woman_jacketAndVest("luxury_woman_jacketAndVest", "��Ŷ/����Ʈ",
			Arrays.asList("luxury_woman_jacketAndVest_jacket", "luxury_woman_jacketAndVest_blazer",
					"luxury_woman_jacketAndVest_leatherJacket", "luxury_woman_jacketAndVest_vest")),

	luxury_woman_jacketAndVest_jacket("luxury_woman_jacketAndVest_jacket", "��Ŷ_4", null),

	luxury_woman_jacketAndVest_blazer("luxury_woman_jacketAndVest_blazer", "������_4", null),

	luxury_woman_jacketAndVest_leatherJacket("luxury_woman_jacketAndVest_leatherJacket", "���� ��Ŷ_4", null),

	luxury_woman_jacketAndVest_vest("luxury_woman_jacketAndVest_vest", "����Ʈ_5", null),

	luxury_woman_tshirt("luxury_woman_tshirt", "Ƽ����",
			Arrays.asList("luxury_woman_tshirt_sleevelong", "luxury_woman_tshirt_sleeveshort",
					"luxury_woman_tshirt_sleeveless")),

	luxury_woman_tshirt_sleevelong("luxury_woman_tshirt_sleevelong", "����_1", null),

	luxury_woman_tshirt_sleeveshort("luxury_woman_tshirt_sleeveshort", "����_1", null),

	luxury_woman_tshirt_sleeveless("luxury_woman_tshirt_sleeveless", "�μҸ�_1", null),

	luxury_woman_shirtAndBlouse("luxury_woman_shirtAndBlouse", "����/���콺",
			Arrays.asList("luxury_woman_shirtAndBlouse_shirtSleeveshort",
					"luxury_woman_shirtAndBlouse_blouseSleeveshort", "luxury_woman_shirtAndBlouse_shirtSleevelong",
					"luxury_woman_shirtAndBlouse_blouseSleevelong", "luxury_woman_shirtAndBlouse_blouseSleeveless")),

	luxury_woman_shirtAndBlouse_shirtSleeveshort("luxury_woman_shirtAndBlouse_shirtSleeveshort", "���� ����_0", null),

	luxury_woman_shirtAndBlouse_blouseSleeveshort("luxury_woman_shirtAndBlouse_blouseSleeveshort", "���� ���콺_0", null),

	luxury_woman_shirtAndBlouse_shirtSleevelong("luxury_woman_shirtAndBlouse_shirtSleevelong", "���� ����_0", null),

	luxury_woman_shirtAndBlouse_blouseSleevelong("luxury_woman_shirtAndBlouse_blouseSleevelong", "���� ���콺_0", null),

	luxury_woman_shirtAndBlouse_blouseSleeveless("luxury_woman_shirtAndBlouse_blouseSleeveless", "�μҸ� ���콺_0", null),

	luxury_woman_neat("luxury_woman_neat", "��Ʈ",
			Arrays.asList("luxury_woman_neat_pullover", "luxury_woman_neat_cardigan", "luxury_woman_neat_vest")),

	luxury_woman_neat_pullover("luxury_woman_neat_pullover", "Ǯ����_2", null),

	luxury_woman_neat_cardigan("luxury_woman_neat_cardigan", "ī���_3", null),

	luxury_woman_neat_vest("luxury_woman_neat_vest", "����Ʈ_5", null),

	luxury_woman_onepiece("luxury_woman_onepiece", "���ǽ�",
			Arrays.asList("luxury_woman_onepiece_sleevelong", "luxury_woman_onepiece_sleeveshort")),

	luxury_woman_onepiece_sleevelong("luxury_woman_onepiece_sleevelong", "����_10", null),

	luxury_woman_onepiece_sleeveshort("luxury_woman_onepiece_sleeveshort", "����/�μҸ�_10", null),

	luxury_woman_pants("luxury_woman_pants", "����",
			Arrays.asList("luxury_woman_pants_long", "luxury_woman_pants_short")),

	luxury_woman_pants_long("luxury_woman_pants_long", "��/�̵� ����_6", null),

	luxury_woman_pants_short("luxury_woman_pants_short", "��Ʈ ����_7", null),

	luxury_woman_skirt("luxury_woman_skirt", "��ĿƮ",
			Arrays.asList("luxury_woman_skirt_long", "luxury_woman_skirt_short")),

	luxury_woman_skirt_long("luxury_woman_skirt_long", "��/�̵�_8", null),

	luxury_woman_skirt_short("luxury_woman_skirt_short", "�̴�_8", null),

	luxury_woman_beachwear("luxury_woman_beachwear", "��ġ����",
			Arrays.asList("luxury_woman_beachwear_swimsuit", "luxury_woman_beachwear_bikini",
					"luxury_woman_beachwear_coverup", "luxury_woman_beachwear_etc")),

	luxury_woman_beachwear_swimsuit("luxury_woman_beachwear_swimsuit", "������Ʈ", null),

	luxury_woman_beachwear_bikini("luxury_woman_beachwear_bikini", "��Ű��", null),

	luxury_woman_beachwear_coverup("luxury_woman_beachwear_coverup", "Ŀ����/�κ�", null),

	luxury_woman_beachwear_etc("luxury_woman_beachwear_etc", "��Ÿ", null),

	luxury_womanBag("luxury_womanBag", "��������/����", Arrays.asList("luxury_womanBag_bag", "luxury_womanBag_purse")),

	luxury_womanBag_bag("luxury_womanBag_bag", "����",
			Arrays.asList("luxury_womanBag_bag_clutch", "luxury_womanBag_bag_backpack", "luxury_womanBag_bag_shopper",
					"luxury_womanBag_bag_carrier", "luxury_womanBag_bag_tote", "luxury_womanBag_bag_cross")),

	luxury_womanBag_bag_clutch("luxury_womanBag_bag_clutch", "Ŭ��ġ", null),

	luxury_womanBag_bag_backpack("luxury_womanBag_bag_backpack", "����", null),

	luxury_womanBag_bag_shopper("luxury_womanBag_bag_shopper", "����", null),

	luxury_womanBag_bag_carrier("luxury_womanBag_bag_carrier", "ĳ����", null),

	luxury_womanBag_bag_tote("luxury_womanBag_bag_tote", "��Ʈ", null),

	luxury_womanBag_bag_cross("luxury_womanBag_bag_cross", "���/ũ�ν�", null),

	luxury_womanBag_purse("luxury_womanBag_purse", "����",
			Arrays.asList("luxury_womanBag_purse_card", "luxury_womanBag_purse_long", "luxury_womanBag_purse_etc",
					"luxury_womanBag_purse_mini")),

	luxury_womanBag_purse_card("luxury_womanBag_purse_card", "����/ī������", null),

	luxury_womanBag_purse_long("luxury_womanBag_purse_long", "������", null),

	luxury_womanBag_purse_etc("luxury_womanBag_purse_etc", "��������/�Ŀ�ġ/��Ÿ", null),

	luxury_womanBag_purse_mini("luxury_womanBag_purse_mini", "�̴�/������", null),

	luxury_womanShoes("luxury_womanShoes", "��������",
			Arrays.asList("luxury_womanShoes_sandal", "luxury_womanShoes_slipper", "luxury_womanShoes_slipon",
					"luxury_womanShoes_flat", "luxury_womanShoes_walker", "luxury_womanShoes_sneakers",
					"luxury_womanShoes_heel")),

	luxury_womanShoes_sandal("luxury_womanShoes_sandal", "����", null),

	luxury_womanShoes_slipper("luxury_womanShoes_slipper", "������/��", null),

	luxury_womanShoes_slipon("luxury_womanShoes_slipon", "������", null),

	luxury_womanShoes_flat("luxury_womanShoes_flat", "�÷�/����", null),

	luxury_womanShoes_walker("luxury_womanShoes_walker", "��Ŀ/����", null),

	luxury_womanShoes_sneakers("luxury_womanShoes_sneakers", "�ȭ/����Ŀ��", null),

	luxury_womanShoes_heel("luxury_womanShoes_heel", "������/��", null),

	luxury_womanWatch("luxury_womanWatch", "���� ���/�ð�",
			Arrays.asList("luxury_womanWatch_earing", "luxury_womanWatch_necklace", "luxury_womanWatch_bracelet",
					"luxury_womanWatch_ring", "luxury_womanWatch_hairAccessory", "luxury_womanWatch_brooch",
					"luxury_womanWatch_watch")),

	luxury_womanWatch_earing("luxury_womanWatch_earing", "�Ͱ���", null),

	luxury_womanWatch_necklace("luxury_womanWatch_necklace", "�����", null),

	luxury_womanWatch_bracelet("luxury_womanWatch_bracelet", "����", null),

	luxury_womanWatch_ring("luxury_womanWatch_ring", "����", null),

	luxury_womanWatch_hairAccessory("luxury_womanWatch_hairAccessory", "���Ǽ�����", null),

	luxury_womanWatch_brooch("luxury_womanWatch_brooch", "���ġ/���Ʈ", null),

	luxury_womanWatch_watch("luxury_womanWatch_watch", "�ð�", null),

	luxury_man("luxury_man", "�����Ƿ�",
			Arrays.asList("luxury_man_outer", "luxury_man_jacket", "luxury_man_tshirt", "luxury_man_shirt",
					"luxury_man_neat", "luxury_man_pants", "luxury_man_beachwear")),

	luxury_man_outer("luxury_man_outer", "�ƿ���",
			Arrays.asList("luxury_man_outer_jumper", "luxury_man_outer_coat", "luxury_man_outer_padding",
					"luxury_man_outer_trench")),

	luxury_man_outer_jumper("luxury_man_outer_jumper", "����_4", null),

	luxury_man_outer_coat("luxury_man_outer_coat", "��Ʈ_9", null),

	luxury_man_outer_padding("luxury_man_outer_padding", "�ٿ�/�е�_4", null),

	luxury_man_outer_trench("luxury_man_outer_trench", "Ʈ��ġ_9", null),

	luxury_man_jacket("luxury_man_jacket", "��Ŷ/����Ʈ",
			Arrays.asList("luxury_man_jacket_jacket", "luxury_man_jacket_blazer", "luxury_man_jacket_leatherJacket",
					"luxury_man_jacket_vest")),

	luxury_man_jacket_jacket("luxury_man_jacket_jacket", "��Ŷ_4", null),

	luxury_man_jacket_blazer("luxury_man_jacket_blazer", "������_4", null),

	luxury_man_jacket_leatherJacket("luxury_man_jacket_leatherJacket", "���� ��Ŷ_4", null),

	luxury_man_jacket_vest("luxury_man_jacket_vest", "����Ʈ_5", null),

	luxury_man_tshirt("luxury_man_tshirt", "Ƽ����",
			Arrays.asList("luxury_man_tshirt_sleeveshort", "luxury_man_tshirt_sleevelong",
					"luxury_man_tshirt_sleeveless")),

	luxury_man_tshirt_sleeveshort("luxury_man_tshirt_sleeveshort", "����_1", null),

	luxury_man_tshirt_sleevelong("luxury_man_tshirt_sleevelong", "����_1", null),

	luxury_man_tshirt_sleeveless("luxury_man_tshirt_sleeveless", "�μҸ�_1", null),

	luxury_man_shirt("luxury_man_shirt", "����",
			Arrays.asList("luxury_man_shirt_sleevelong", "luxury_man_shirt_sleeveshort")),

	luxury_man_shirt_sleevelong("luxury_man_shirt_sleevelong", "����_0", null),

	luxury_man_shirt_sleeveshort("luxury_man_shirt_sleeveshort", "����_0", null),

	luxury_man_neat("luxury_man_neat", "��Ʈ",
			Arrays.asList("luxury_man_neat_cardigan", "luxury_man_neat_pullover", "luxury_man_neat_vest")),

	luxury_man_neat_cardigan("luxury_man_neat_cardigan", "ī���_3", null),

	luxury_man_neat_pullover("luxury_man_neat_pullover", "Ǯ����_2", null),

	luxury_man_neat_vest("luxury_man_neat_vest", "����Ʈ_5", null),

	luxury_man_pants("luxury_man_pants", "����",
			Arrays.asList("luxury_man_pants_casualLong", "luxury_man_pants_denim", "luxury_man_pants_formal",
					"luxury_man_pants_suitPants", "luxury_man_pants_casualShort")),

	luxury_man_pants_casualLong("luxury_man_pants_casualLong", "ĳ�־� �� ����_6", null),

	luxury_man_pants_denim("luxury_man_pants_denim", "����_6", null),

	luxury_man_pants_formal("luxury_man_pants_formal", "����_6", null),

	luxury_man_pants_suitPants("luxury_man_pants_suitPants", "����_6", null),

	luxury_man_pants_casualShort("luxury_man_pants_casualShort", "ĳ�־� ��Ʈ ����_7", null),

	luxury_man_beachwear("luxury_man_beachwear", "��ġ����",
			Arrays.asList("luxury_man_beachwear_swim", "luxury_man_beachwear_pants", "luxury_man_beachwear_etc")),

	luxury_man_beachwear_swim("luxury_man_beachwear_swim", "������", null),

	luxury_man_beachwear_pants("luxury_man_beachwear_pants", "��ġ����", null),

	luxury_man_beachwear_etc("luxury_man_beachwear_etc", "��Ÿ", null),

	luxury_manBag("luxury_manBag", "��������/����", Arrays.asList("luxury_manBag_bag", "luxury_manBag_purse")),

	luxury_manBag_bag("luxury_manBag_bag", "����",
			Arrays.asList("luxury_manBag_bag_clutch", "luxury_manBag_bag_tote", "luxury_manBag_bag_sling",
					"luxury_manBag_bag_briefcase", "luxury_manBag_bag_carrier", "luxury_manBag_bag_shoulder",
					"luxury_manBag_bag_backpack")),

	luxury_manBag_bag_clutch("luxury_manBag_bag_clutch", "Ŭ��ġ", null),

	luxury_manBag_bag_tote("luxury_manBag_bag_tote", "��Ʈ", null),

	luxury_manBag_bag_sling("luxury_manBag_bag_sling", "������/����", null),

	luxury_manBag_bag_briefcase("luxury_manBag_bag_briefcase", "�긮�����̽�", null),

	luxury_manBag_bag_carrier("luxury_manBag_bag_carrier", "ĳ����", null),

	luxury_manBag_bag_shoulder("luxury_manBag_bag_shoulder", "���/�޽���", null),

	luxury_manBag_bag_backpack("luxury_manBag_bag_backpack", "����", null),

	luxury_manBag_purse("luxury_manBag_purse", "����",
			Arrays.asList("luxury_manBag_purse_moneyclip", "luxury_manBag_purse_card", "luxury_manBag_purse_long",
					"luxury_manBag_purse_keyholder", "luxury_manBag_purse_half", "luxury_manBag_purse_medium")),

	luxury_manBag_purse_moneyclip("luxury_manBag_purse_moneyclip", "�Ӵ�Ŭ��", null),

	luxury_manBag_purse_card("luxury_manBag_purse_card", "����/ī������", null),

	luxury_manBag_purse_long("luxury_manBag_purse_long", "������", null),

	luxury_manBag_purse_keyholder("luxury_manBag_purse_keyholder", "ŰȦ��/��������", null),

	luxury_manBag_purse_half("luxury_manBag_purse_half", "������", null),

	luxury_manBag_purse_medium("luxury_manBag_purse_medium", "������", null),

	luxury_manShoes("luxury_manShoes", "��������",
			Arrays.asList("luxury_manShoes_slipon", "luxury_manShoes_shoes", "luxury_manShoes_slipper",
					"luxury_manShoes_sandal", "luxury_manShoes_sneakers", "luxury_manShoes_walker")),

	luxury_manShoes_slipon("luxury_manShoes_slipon", "������", null),

	luxury_manShoes_shoes("luxury_manShoes_shoes", "����", null),

	luxury_manShoes_slipper("luxury_manShoes_slipper", "������/��", null),

	luxury_manShoes_sandal("luxury_manShoes_sandal", "����", null),

	luxury_manShoes_sneakers("luxury_manShoes_sneakers", "�ȭ/����Ŀ��", null),

	luxury_manShoes_walker("luxury_manShoes_walker", "��Ŀ/����", null),

	luxury_manWatch("luxury_manWatch", "���� ���/�ð�",
			Arrays.asList("luxury_manWatch_jewerly", "luxury_manWatch_watch", "luxury_manWatch_etc")),

	luxury_manWatch_jewerly("luxury_manWatch_jewerly", "���", null),

	luxury_manWatch_watch("luxury_manWatch_watch", "�ð�", null),

	luxury_manWatch_etc("luxury_manWatch_etc", "��Ÿ", null),

	sports("sports", "������",
			Arrays.asList("sports_outdoor", "sports_running", "sports_yoga", "sports_fitness", "sports_athleisure",
					"sports_tennis", "sports_swim", "sports_soccer")),

	sports_outdoor("sports_outdoor", "�ƿ�����/ķ��",
			Arrays.asList("sports_outdoor_man", "sports_outdoor_woman", "sports_outdoor_shoes",
					"sports_outdoor_headgear", "sports_outdoor_bag")),

	sports_outdoor_man("sports_outdoor_man", "���� �Ƿ�",
			Arrays.asList("sports_outdoor_man_outer", "sports_outdoor_man_jacket", "sports_outdoor_man_hoodie",
					"sports_outdoor_man_tshirt", "sports_outdoor_man_pants")),

	sports_outdoor_man_outer("sports_outdoor_man_outer", "�ƿ���_4", null),

	sports_outdoor_man_jacket("sports_outdoor_man_jacket", "��Ŷ/����Ʈ_4", null),

	sports_outdoor_man_hoodie("sports_outdoor_man_hoodie", "������/�ĵ�_1", null),

	sports_outdoor_man_tshirt("sports_outdoor_man_tshirt", "Ƽ����/�����긮��_1", null),

	sports_outdoor_man_pants("sports_outdoor_man_pants", "����/Ÿ����_6", null),

	sports_outdoor_woman("sports_outdoor_woman", "���� �Ƿ�",
			Arrays.asList("sports_outdoor_woman_outer", "sports_outdoor_woman_jacket", "sports_outdoor_woman_hoodie",
					"sports_outdoor_woman_tshirt", "sports_outdoor_woman_pants")),

	sports_outdoor_woman_outer("sports_outdoor_woman_outer", "�ƿ���_4", null),

	sports_outdoor_woman_jacket("sports_outdoor_woman_jacket", "��Ŷ/����Ʈ_4", null),

	sports_outdoor_woman_hoodie("sports_outdoor_woman_hoodie", "������/�ĵ�_1", null),

	sports_outdoor_woman_tshirt("sports_outdoor_woman_tshirt", "Ƽ����/�����긮��_1", null),

	sports_outdoor_woman_pants("sports_outdoor_woman_pants", "����/Ÿ����_6", null),

	sports_outdoor_shoes("sports_outdoor_shoes", "����",
			Arrays.asList("sports_outdoor_shoes_tracking", "sports_outdoor_shoes_hicking",
					"sports_outdoor_shoes_walking")),

	sports_outdoor_shoes_tracking("sports_outdoor_shoes_tracking", "Ʈ��ŷ", null),

	sports_outdoor_shoes_hicking("sports_outdoor_shoes_hicking", "����ŷ", null),

	sports_outdoor_shoes_walking("sports_outdoor_shoes_walking", "��ŷ", null),

	sports_outdoor_headgear("sports_outdoor_headgear", "�����", null),

	sports_outdoor_bag("sports_outdoor_bag", "�賶",
			Arrays.asList("sports_outdoor_bag_hicking", "sports_outdoor_bag_lifestyle")),

	sports_outdoor_bag_hicking("sports_outdoor_bag_hicking", "����ŷ", null),

	sports_outdoor_bag_lifestyle("sports_outdoor_bag_lifestyle", "��������Ÿ��", null),

	sports_running("sports_running", "����/����Ŭ",
			Arrays.asList("sports_running_man", "sports_running_woman", "sports_running_running",
					"sports_running_cycle")),

	sports_running_man("sports_running_man", "���� �Ƿ�",
			Arrays.asList("sports_running_man_outer", "sports_running_man_jacket", "sports_running_man_hoodie",
					"sports_running_man_tshirt", "sports_running_man_pants")),

	sports_running_man_outer("sports_running_man_outer", "�ƿ���_4", null),

	sports_running_man_jacket("sports_running_man_jacket", "��Ŷ/����Ʈ_4", null),

	sports_running_man_hoodie("sports_running_man_hoodie", "������/�ĵ�_1", null),

	sports_running_man_tshirt("sports_running_man_tshirt", "Ƽ����/�����긮��_1", null),

	sports_running_man_pants("sports_running_man_pants", "����/Ÿ����_6", null),

	sports_running_woman("sports_running_woman", "���� �Ƿ�",
			Arrays.asList("sports_running_woman_outer", "sports_running_woman_jacket", "sports_running_woman_hoodie",
					"sports_running_woman_tshirt", "sports_running_woman_pants")),

	sports_running_woman_outer("sports_running_woman_outer", "�ƿ���_4", null),

	sports_running_woman_jacket("sports_running_woman_jacket", "��Ŷ/����Ʈ_4", null),

	sports_running_woman_hoodie("sports_running_woman_hoodie", "������/�ĵ�_1", null),

	sports_running_woman_tshirt("sports_running_woman_tshirt", "Ƽ����/�����긮��_1", null),

	sports_running_woman_pants("sports_running_woman_pants", "����/Ÿ����_6", null),

	sports_running_running("sports_running_running", "���� ��ǰ",
			Arrays.asList("sports_running_running_shoes", "sports_running_running_accessory")),

	sports_running_running_shoes("sports_running_running_shoes", "����ȭ", null),

	sports_running_running_accessory("sports_running_running_accessory", "�׼�����", null),

	sports_running_cycle("sports_running_cycle", "����Ŭ ��ǰ",
			Arrays.asList("sports_running_cycle_shoes", "sports_running_cycle_accessory")),

	sports_running_cycle_shoes("sports_running_cycle_shoes", "����Ŭȭ", null),

	sports_running_cycle_accessory("sports_running_cycle_accessory", "�׼�����", null),

	sports_yoga("sports_yoga", "�䰡/�ʶ��׽�", Arrays.asList("sports_yoga_woman", "sports_yoga_supplies")),

	sports_yoga_woman("sports_yoga_woman", "���� �Ƿ�",
			Arrays.asList("sports_yoga_woman_outer", "sports_yoga_woman_top", "sports_yoga_woman_pants")),

	sports_yoga_woman_outer("sports_yoga_woman_outer", "�ƿ���_4", null),

	sports_yoga_woman_top("sports_yoga_woman_top", "ž_1", null),

	sports_yoga_woman_pants("sports_yoga_woman_pants", "����_6", null),

	sports_yoga_supplies("sports_yoga_supplies", "��ǰ",
			Arrays.asList("sports_yoga_supplies_mat", "sports_yoga_supplies_hat", "sports_yoga_supplies_etc")),

	sports_yoga_supplies_mat("sports_yoga_supplies_mat", "��Ʈ", null),

	sports_yoga_supplies_hat("sports_yoga_supplies_hat", "����/�����", null),

	sports_yoga_supplies_etc("sports_yoga_supplies_etc", "��Ÿ��ǰ", null),

	sports_fitness("sports_fitness", "��Ʈ�Ͻ�",
			Arrays.asList("sports_fitness_man", "sports_fitness_woman", "sports_fitness_supplies")),

	sports_fitness_man("sports_fitness_man", "�����Ƿ�",
			Arrays.asList("sports_fitness_man_outer", "sports_fitness_man_jacket", "sports_fitness_man_hoodie",
					"sports_fitness_man_tshirt", "sports_fitness_man_pants")),

	sports_fitness_man_outer("sports_fitness_man_outer", "�ƿ���_4", null),

	sports_fitness_man_jacket("sports_fitness_man_jacket", "��Ŷ/����Ʈ_4", null),

	sports_fitness_man_hoodie("sports_fitness_man_hoodie", "������/�ĵ�_1", null),

	sports_fitness_man_tshirt("sports_fitness_man_tshirt", "Ƽ����/�����긮��_1", null),

	sports_fitness_man_pants("sports_fitness_man_pants", "����/Ÿ����_6", null),

	sports_fitness_woman("sports_fitness_woman", "�����Ƿ�",
			Arrays.asList("sports_fitness_woman_outer", "sports_fitness_woman_jacket", "sports_fitness_woman_hoodie",
					"sports_fitness_woman_tshirt", "sports_fitness_woman_pants")),

	sports_fitness_woman_outer("sports_fitness_woman_outer", "�ƿ���_4", null),

	sports_fitness_woman_jacket("sports_fitness_woman_jacket", "��Ŷ/����Ʈ_4", null),

	sports_fitness_woman_hoodie("sports_fitness_woman_hoodie", "������/�ĵ�_1", null),

	sports_fitness_woman_tshirt("sports_fitness_woman_tshirt", "Ƽ����/�����긮��_1", null),

	sports_fitness_woman_pants("sports_fitness_woman_pants", "����/Ÿ����_6", null),

	sports_fitness_supplies("sports_fitness_supplies", "��ǰ",
			Arrays.asList("sports_fitness_supplies_shoes", "sports_fitness_supplies_accessory")),

	sports_fitness_supplies_accessory("sports_fitness_supplies_accessory", "�׼�����", null),

	sports_fitness_supplies_shoes("sports_fitness_supplies_shoes", "����", null),

	sports_athleisure("sports_athleisure", "�ֽ�����",
			Arrays.asList("sports_athleisure_man", "sports_athleisure_woman", "sports_athleisure_supplies")),

	sports_athleisure_man("sports_athleisure_man", "���� �Ƿ�",
			Arrays.asList("sports_athleisure_man_outer", "sports_athleisure_man_top", "sports_athleisure_man_pants")),

	sports_athleisure_man_outer("sports_athleisure_man_outer", "�ƿ���_4", null),

	sports_athleisure_man_top("sports_athleisure_man_top", "ž_1", null),

	sports_athleisure_man_pants("sports_athleisure_man_pants", "����_6", null),

	sports_athleisure_woman("sports_athleisure_woman", "���� �Ƿ�",
			Arrays.asList("sports_athleisure_woman_outer", "sports_athleisure_woman_top",
					"sports_athleisure_woman_pants")),

	sports_athleisure_woman_outer("sports_athleisure_woman_outer", "�ƿ���_4", null),

	sports_athleisure_woman_top("sports_athleisure_woman_top", "ž_1", null),

	sports_athleisure_woman_pants("sports_athleisure_woman_pants", "����_6", null),

	sports_athleisure_supplies("sports_athleisure_supplies", "��ǰ",
			Arrays.asList("sports_athleisure_supplies_shoes", "sports_athleisure_supplies_accessory",
					"sports_athleisure_supplies_etc")),

	sports_athleisure_supplies_shoes("sports_athleisure_supplies_shoes", "����", null),

	sports_athleisure_supplies_accessory("sports_athleisure_supplies_accessory", "�׼�����", null),

	sports_athleisure_supplies_etc("sports_athleisure_supplies_etc", "��Ÿ��ǰ", null),

	sports_tennis("sports_tennis", "�״Ͻ�",
			Arrays.asList("sports_tennis_man", "sports_tennis_woman", "sports_tennis_supplies")),

	sports_tennis_man("sports_tennis_man", "�����Ƿ�",
			Arrays.asList("sports_tennis_man_outer", "sports_tennis_man_top", "sports_tennis_man_pants")),

	sports_tennis_man_outer("sports_tennis_man_outer", "�ƿ���_4", null),

	sports_tennis_man_top("sports_tennis_man_top", "ž_1", null),

	sports_tennis_man_pants("sports_tennis_man_pants", "����_6", null),

	sports_tennis_woman("sports_tennis_woman", "�����Ƿ�",
			Arrays.asList("sports_tennis_woman_outer", "sports_tennis_woman_top", "sports_tennis_woman_pants")),

	sports_tennis_woman_outer("sports_tennis_woman_outer", "�ƿ���_4", null),

	sports_tennis_woman_top("sports_tennis_woman_top", "ž_1", null),

	sports_tennis_woman_pants("sports_tennis_woman_pants", "����/��ĿƮ_6", null),

	sports_tennis_supplies("sports_tennis_supplies", "��ǰ",
			Arrays.asList("sports_tennis_supplies_bag", "sports_tennis_supplies_shoes",
					"sports_tennis_supplies_accessory", "sports_tennis_supplies_etc")),

	sports_tennis_supplies_bag("sports_tennis_supplies_bag", "�״Ͻ� ����", null),

	sports_tennis_supplies_shoes("sports_tennis_supplies_shoes", "����", null),

	sports_tennis_supplies_accessory("sports_tennis_supplies_accessory", "�׼�����", null),

	sports_tennis_supplies_etc("sports_tennis_supplies_etc", "��Ÿ��ǰ", null),

	sports_swim("sports_swim", "����", Arrays.asList("sports_swim_man", "sports_swim_woman", "sports_swim_supplies")),

	sports_swim_man("sports_swim_man", "���� �Ƿ�",
			Arrays.asList("sports_swim_man_indoors", "sports_swim_man_beachwearTop",
					"sports_swim_man_beachwearBottom")),

	sports_swim_man_indoors("sports_swim_man_indoors", "�ǳ� ������", null),

	sports_swim_man_beachwearTop("sports_swim_man_beachwearTop", "��ġ���� ����", null),

	sports_swim_man_beachwearBottom("sports_swim_man_beachwearBottom", "��ġ���� ����", null),

	sports_swim_woman("sports_swim_woman", "���� �Ƿ�",
			Arrays.asList("sports_swim_woman_general", "sports_swim_woman_bikini", "sports_swim_woman_beachwearTop",
					"sports_swim_woman_beachwearBottom")),

	sports_swim_woman_general("sports_swim_woman_general", "���� ������", null),

	sports_swim_woman_bikini("sports_swim_woman_bikini", "��Ű��", null),

	sports_swim_woman_beachwearTop("sports_swim_woman_beachwearTop", "��ġ���� ����", null),

	sports_swim_woman_beachwearBottom("sports_swim_woman_beachwearBottom", "��ġ���� ����", null),

	sports_swim_supplies("sports_swim_supplies", "��ǰ",
			Arrays.asList("sports_swim_supplies_swim", "sports_swim_supplies_shoes", "sports_swim_supplies_accessory")),

	sports_swim_supplies_swim("sports_swim_supplies_swim", "������ǰ", null),

	sports_swim_supplies_shoes("sports_swim_supplies_shoes", "����", null),

	sports_swim_supplies_accessory("sports_swim_supplies_accessory", "�׼�����", null),

	sports_soccer("sports_soccer", "�౸",
			Arrays.asList("sports_soccer_uniform", "sports_soccer_shoes", "sports_soccer_supplies")),

	sports_soccer_uniform("sports_soccer_uniform", "������", null),

	sports_soccer_shoes("sports_soccer_shoes", "�౸ȭ", null),

	sports_soccer_supplies("sports_soccer_supplies", "�౸��ǰ", null),

	bagAndShoes("bagAndShoes", "�飦����",
			Arrays.asList("bagAndShoes_womanBag", "bagAndShoes_womanPurse", "bagAndShoes_womanShoes",
					"bagAndShoes_manBag", "bagAndShoes_manPurse", "bagAndShoes_manShoes")),

	bagAndShoes_womanBag("bagAndShoes_womanBag", "���� ����",
			Arrays.asList("bagAndShoes_womanBag_tote", "bagAndShoes_womanBag_cross", "bagAndShoes_womanBag_shopper",
					"bagAndShoes_womanBag_clutch", "bagAndShoes_womanBag_backpack", "bagAndShoes_womanBag_echo",
					"bagAndShoes_womanBag_mini", "bagAndShoes_womanBag_carrier")),

	bagAndShoes_womanBag_tote("bagAndShoes_womanBag_tote", "��Ʈ", null),

	bagAndShoes_womanBag_cross("bagAndShoes_womanBag_cross", "���/ũ�ν�", null),

	bagAndShoes_womanBag_shopper("bagAndShoes_womanBag_shopper", "����", null),

	bagAndShoes_womanBag_clutch("bagAndShoes_womanBag_clutch", "Ŭ��ġ", null),

	bagAndShoes_womanBag_backpack("bagAndShoes_womanBag_backpack", "����", null),

	bagAndShoes_womanBag_echo("bagAndShoes_womanBag_echo", "���ڹ�", null),

	bagAndShoes_womanBag_mini("bagAndShoes_womanBag_mini", "�̴Ϲ�", null),

	bagAndShoes_womanBag_carrier("bagAndShoes_womanBag_carrier", "ĳ����", null),

	bagAndShoes_womanPurse("bagAndShoes_womanPurse", "���� ����",
			Arrays.asList("bagAndShoes_womanPurse_mini", "bagAndShoes_womanPurse_medium", "bagAndShoes_womanPurse_long",
					"bagAndShoes_womanPurse_card", "bagAndShoes_womanPurse_etc")),

	bagAndShoes_womanPurse_mini("bagAndShoes_womanPurse_mini", "�̴�/������", null),

	bagAndShoes_womanPurse_medium("bagAndShoes_womanPurse_medium", "������", null),

	bagAndShoes_womanPurse_long("bagAndShoes_womanPurse_long", "������", null),

	bagAndShoes_womanPurse_card("bagAndShoes_womanPurse_card", "����/ī������", null),

	bagAndShoes_womanPurse_etc("bagAndShoes_womanPurse_etc", "��������/�Ŀ�ġ/��Ÿ", null),

	bagAndShoes_womanShoes("bagAndShoes_womanShoes", "���� ����",
			Arrays.asList("bagAndShoes_womanShoes_heel", "bagAndShoes_womanShoes_flat", "bagAndShoes_womanShoes_sandal",
					"bagAndShoes_womanShoes_slipper", "bagAndShoes_womanShoes_sneakers",
					"bagAndShoes_womanShoes_slipon", "bagAndShoes_womanShoes_walker",
					"bagAndShoes_womanShoes_mountain")),

	bagAndShoes_womanShoes_heel("bagAndShoes_womanShoes_heel", "������/��", null),

	bagAndShoes_womanShoes_flat("bagAndShoes_womanShoes_flat", "�÷�/����", null),

	bagAndShoes_womanShoes_sandal("bagAndShoes_womanShoes_sandal", "����", null),

	bagAndShoes_womanShoes_slipper("bagAndShoes_womanShoes_slipper", "������/��", null),

	bagAndShoes_womanShoes_sneakers("bagAndShoes_womanShoes_sneakers", "�ȭ/����Ŀ��", null),

	bagAndShoes_womanShoes_slipon("bagAndShoes_womanShoes_slipon", "������", null),

	bagAndShoes_womanShoes_walker("bagAndShoes_womanShoes_walker", "��Ŀ/����", null),

	bagAndShoes_womanShoes_mountain("bagAndShoes_womanShoes_mountain", "���ȭ/����ȭ", null),

	bagAndShoes_manBag("bagAndShoes_manBag", "���� ����",
			Arrays.asList("bagAndShoes_manBag_shoulder", "bagAndShoes_manBag_backpack", "bagAndShoes_manBag_briefcase",
					"bagAndShoes_manBag_clutch", "bagAndShoes_manBag_tote", "bagAndShoes_manBag_sling",
					"bagAndShoes_manBag_echo", "bagAndShoes_manBag_carrier")),

	bagAndShoes_manBag_shoulder("bagAndShoes_manBag_shoulder", "���/�޽���", null),

	bagAndShoes_manBag_backpack("bagAndShoes_manBag_backpack", "����", null),

	bagAndShoes_manBag_briefcase("bagAndShoes_manBag_briefcase", "�긮�����̽�", null),

	bagAndShoes_manBag_clutch("bagAndShoes_manBag_clutch", "Ŭ��ġ", null),

	bagAndShoes_manBag_tote("bagAndShoes_manBag_tote", "��Ʈ", null),

	bagAndShoes_manBag_sling("bagAndShoes_manBag_sling", "������/����", null),

	bagAndShoes_manBag_echo("bagAndShoes_manBag_echo", "���ڹ�", null),

	bagAndShoes_manBag_carrier("bagAndShoes_manBag_carrier", "ĳ����", null),

	bagAndShoes_manPurse("bagAndShoes_manPurse", "���� ����",
			Arrays.asList("bagAndShoes_manPurse_half", "bagAndShoes_manPurse_medium", "bagAndShoes_manPurse_long",
					"bagAndShoes_manPurse_moneyclip", "bagAndShoes_manPurse_card", "bagAndShoes_manPurse_keyholder")),

	bagAndShoes_manPurse_half("bagAndShoes_manPurse_half", "������", null),

	bagAndShoes_manPurse_medium("bagAndShoes_manPurse_medium", "������", null),

	bagAndShoes_manPurse_long("bagAndShoes_manPurse_long", "������", null),

	bagAndShoes_manPurse_moneyclip("bagAndShoes_manPurse_moneyclip", "�Ӵ�Ŭ��", null),

	bagAndShoes_manPurse_card("bagAndShoes_manPurse_card", "����/ī������", null),

	bagAndShoes_manPurse_keyholder("bagAndShoes_manPurse_keyholder", "ŰȦ��/��������", null),

	bagAndShoes_manShoes("bagAndShoes_manShoes", "���� ����",
			Arrays.asList("bagAndShoes_manShoes_sneakers", "bagAndShoes_manShoes_walker",
					"bagAndShoes_manShoes_slipper", "bagAndShoes_manShoes_slipon", "bagAndShoes_manShoes_shoes",
					"bagAndShoes_manShoes_sandal", "bagAndShoes_manShoes_mountain")),

	bagAndShoes_manShoes_sneakers("bagAndShoes_manShoes_sneakers", "�ȭ/����Ŀ��", null),

	bagAndShoes_manShoes_walker("bagAndShoes_manShoes_walker", "��Ŀ/����", null),

	bagAndShoes_manShoes_slipper("bagAndShoes_manShoes_slipper", "������/��", null),

	bagAndShoes_manShoes_slipon("bagAndShoes_manShoes_slipon", "������", null),

	bagAndShoes_manShoes_shoes("bagAndShoes_manShoes_shoes", "����", null),

	bagAndShoes_manShoes_sandal("bagAndShoes_manShoes_sandal", "����", null),

	bagAndShoes_manShoes_mountain("bagAndShoes_manShoes_mountain", "���ȭ/����ȭ", null);

	private final String name;
	private final String name_kor;
	private final List<String> children;

	public String getSelf() {
		String[] nameArr = name.split("_");
		return nameArr[nameArr.length - 1];
	}

	public String getParent() {
		String[] nameArr = name.split("_");

		if (nameArr.length == 1) {
			return null;
		} else {
			return String.join("_", Arrays.copyOfRange(nameArr, 0, nameArr.length - 1));
		}
	}

	public String getNameKor() {
		return name_kor;
	}

	public List<String> getChildren() {
		return children;
	}

	CategoryEnum(String name, String name_kor, List<String> children) {
		this.name = name;
		this.name_kor = name_kor;
		this.children = children;
	}

}
