package org.koi.gameobject.typeline;

import java.math.BigInteger;

import static org.koi.util.Constants.typeToIndex;

public class TypelineBuilder {
	private BigInteger value = BigInteger.ZERO;

	public TypelineBuilder() { }

	public TypelineBuilder basic() {
		value = value.setBit(typeToIndex("Basic"));
		return this;
	}
	public TypelineBuilder legendary() {
		value = value.setBit(typeToIndex("Legendary"));
		return this;
	}
	public TypelineBuilder snow() {
		value = value.setBit(typeToIndex("Snow"));
		return this;
	}
	public TypelineBuilder world() {
		value = value.setBit(typeToIndex("World"));
		return this;
	}
	public TypelineBuilder instant() {
		value = value.setBit(typeToIndex("Instant"));
		return this;
	}
	public TypelineBuilder sorcery() {
		value = value.setBit(typeToIndex("Sorcery"));
		return this;
	}
	public TypelineBuilder artifact() {
		value = value.setBit(typeToIndex("Artifact"));
		return this;
	}
	public TypelineBuilder creature() {
		value = value.setBit(typeToIndex("Creature"));
		return this;
	}
	public TypelineBuilder enchantment() {
		value = value.setBit(typeToIndex("Enchantment"));
		return this;
	}
	public TypelineBuilder land() {
		value = value.setBit(typeToIndex("Land"));
		return this;
	}
	public TypelineBuilder planeswalker() {
		value = value.setBit(typeToIndex("Planeswalker"));
		return this;
	}
	public TypelineBuilder battle() {
		value = value.setBit(typeToIndex("Battle"));
		return this;
	}
	public TypelineBuilder kindred() {
		value = value.setBit(typeToIndex("Kindred"));
		return this;
	}
	public TypelineBuilder attraction() {
		value = value.setBit(typeToIndex("Attraction"));
		return this;
	}
	public TypelineBuilder blood() {
		value = value.setBit(typeToIndex("Blood"));
		return this;
	}
	public TypelineBuilder bobblehead() {
		value = value.setBit(typeToIndex("Bobblehead"));
		return this;
	}
	public TypelineBuilder clue() {
		value = value.setBit(typeToIndex("Clue"));
		return this;
	}
	public TypelineBuilder contraption() {
		value = value.setBit(typeToIndex("Contraption"));
		return this;
	}
	public TypelineBuilder equipment() {
		value = value.setBit(typeToIndex("Equipment"));
		return this;
	}
	public TypelineBuilder food() {
		value = value.setBit(typeToIndex("Food"));
		return this;
	}
	public TypelineBuilder fortification() {
		value = value.setBit(typeToIndex("Fortification"));
		return this;
	}
	public TypelineBuilder gold() {
		value = value.setBit(typeToIndex("Gold"));
		return this;
	}
	public TypelineBuilder incubator() {
		value = value.setBit(typeToIndex("Incubator"));
		return this;
	}
	public TypelineBuilder junk() {
		value = value.setBit(typeToIndex("Junk"));
		return this;
	}
	public TypelineBuilder map() {
		value = value.setBit(typeToIndex("Map"));
		return this;
	}
	public TypelineBuilder powerstone() {
		value = value.setBit(typeToIndex("Powerstone"));
		return this;
	}
	public TypelineBuilder treasure() {
		value = value.setBit(typeToIndex("Treasure"));
		return this;
	}
	public TypelineBuilder vehicle() {
		value = value.setBit(typeToIndex("Vehicle"));
		return this;
	}
	public TypelineBuilder aura() {
		value = value.setBit(typeToIndex("Aura"));
		return this;
	}
	public TypelineBuilder background() {
		value = value.setBit(typeToIndex("Background"));
		return this;
	}
	public TypelineBuilder cartouche() {
		value = value.setBit(typeToIndex("Cartouche"));
		return this;
	}
	public TypelineBuilder case_() {
		value = value.setBit(typeToIndex("Case_"));
		return this;
	}
	public TypelineBuilder class_() {
		value = value.setBit(typeToIndex("Class_"));
		return this;
	}
	public TypelineBuilder curse() {
		value = value.setBit(typeToIndex("Curse"));
		return this;
	}
	public TypelineBuilder role() {
		value = value.setBit(typeToIndex("Role"));
		return this;
	}
	public TypelineBuilder room() {
		value = value.setBit(typeToIndex("Room"));
		return this;
	}
	public TypelineBuilder rune() {
		value = value.setBit(typeToIndex("Rune"));
		return this;
	}
	public TypelineBuilder saga() {
		value = value.setBit(typeToIndex("Saga"));
		return this;
	}
	public TypelineBuilder shard() {
		value = value.setBit(typeToIndex("Shard"));
		return this;
	}
	public TypelineBuilder shrine() {
		value = value.setBit(typeToIndex("Shrine"));
		return this;
	}
	public TypelineBuilder cave() {
		value = value.setBit(typeToIndex("Cave"));
		return this;
	}
	public TypelineBuilder desert() {
		value = value.setBit(typeToIndex("Desert"));
		return this;
	}
	public TypelineBuilder forest() {
		value = value.setBit(typeToIndex("Forest"));
		return this;
	}
	public TypelineBuilder gate() {
		value = value.setBit(typeToIndex("Gate"));
		return this;
	}
	public TypelineBuilder island() {
		value = value.setBit(typeToIndex("Island"));
		return this;
	}
	public TypelineBuilder lair() {
		value = value.setBit(typeToIndex("Lair"));
		return this;
	}
	public TypelineBuilder locus() {
		value = value.setBit(typeToIndex("Locus"));
		return this;
	}
	public TypelineBuilder mine() {
		value = value.setBit(typeToIndex("Mine"));
		return this;
	}
	public TypelineBuilder mountain() {
		value = value.setBit(typeToIndex("Mountain"));
		return this;
	}
	public TypelineBuilder plains() {
		value = value.setBit(typeToIndex("Plains"));
		return this;
	}
	public TypelineBuilder power_plant() {
		value = value.setBit(typeToIndex("Power_Plant"));
		return this;
	}
	public TypelineBuilder sphere() {
		value = value.setBit(typeToIndex("Sphere"));
		return this;
	}
	public TypelineBuilder swamp() {
		value = value.setBit(typeToIndex("Swamp"));
		return this;
	}
	public TypelineBuilder tower() {
		value = value.setBit(typeToIndex("Tower"));
		return this;
	}
	public TypelineBuilder urza_s() {
		value = value.setBit(typeToIndex("Urza_s"));
		return this;
	}
	public TypelineBuilder ajani() {
		value = value.setBit(typeToIndex("Ajani"));
		return this;
	}
	public TypelineBuilder aminatou() {
		value = value.setBit(typeToIndex("Aminatou"));
		return this;
	}
	public TypelineBuilder angrath() {
		value = value.setBit(typeToIndex("Angrath"));
		return this;
	}
	public TypelineBuilder arlinn() {
		value = value.setBit(typeToIndex("Arlinn"));
		return this;
	}
	public TypelineBuilder ashiok() {
		value = value.setBit(typeToIndex("Ashiok"));
		return this;
	}
	public TypelineBuilder bahamut() {
		value = value.setBit(typeToIndex("Bahamut"));
		return this;
	}
	public TypelineBuilder basri() {
		value = value.setBit(typeToIndex("Basri"));
		return this;
	}
	public TypelineBuilder bolas() {
		value = value.setBit(typeToIndex("Bolas"));
		return this;
	}
	public TypelineBuilder calix() {
		value = value.setBit(typeToIndex("Calix"));
		return this;
	}
	public TypelineBuilder chandra() {
		value = value.setBit(typeToIndex("Chandra"));
		return this;
	}
	public TypelineBuilder comet() {
		value = value.setBit(typeToIndex("Comet"));
		return this;
	}
	public TypelineBuilder dack() {
		value = value.setBit(typeToIndex("Dack"));
		return this;
	}
	public TypelineBuilder dakkon() {
		value = value.setBit(typeToIndex("Dakkon"));
		return this;
	}
	public TypelineBuilder daretti() {
		value = value.setBit(typeToIndex("Daretti"));
		return this;
	}
	public TypelineBuilder davriel() {
		value = value.setBit(typeToIndex("Davriel"));
		return this;
	}
	public TypelineBuilder dihada() {
		value = value.setBit(typeToIndex("Dihada"));
		return this;
	}
	public TypelineBuilder domri() {
		value = value.setBit(typeToIndex("Domri"));
		return this;
	}
	public TypelineBuilder dovin() {
		value = value.setBit(typeToIndex("Dovin"));
		return this;
	}
	public TypelineBuilder ellywick() {
		value = value.setBit(typeToIndex("Ellywick"));
		return this;
	}
	public TypelineBuilder elminster() {
		value = value.setBit(typeToIndex("Elminster"));
		return this;
	}
	public TypelineBuilder elspeth() {
		value = value.setBit(typeToIndex("Elspeth"));
		return this;
	}
	public TypelineBuilder estrid() {
		value = value.setBit(typeToIndex("Estrid"));
		return this;
	}
	public TypelineBuilder freyalise() {
		value = value.setBit(typeToIndex("Freyalise"));
		return this;
	}
	public TypelineBuilder garruk() {
		value = value.setBit(typeToIndex("Garruk"));
		return this;
	}
	public TypelineBuilder gideon() {
		value = value.setBit(typeToIndex("Gideon"));
		return this;
	}
	public TypelineBuilder grist() {
		value = value.setBit(typeToIndex("Grist"));
		return this;
	}
	public TypelineBuilder guff() {
		value = value.setBit(typeToIndex("Guff"));
		return this;
	}
	public TypelineBuilder huatli() {
		value = value.setBit(typeToIndex("Huatli"));
		return this;
	}
	public TypelineBuilder jace() {
		value = value.setBit(typeToIndex("Jace"));
		return this;
	}
	public TypelineBuilder jared() {
		value = value.setBit(typeToIndex("Jared"));
		return this;
	}
	public TypelineBuilder jaya() {
		value = value.setBit(typeToIndex("Jaya"));
		return this;
	}
	public TypelineBuilder jeska() {
		value = value.setBit(typeToIndex("Jeska"));
		return this;
	}
	public TypelineBuilder kaito() {
		value = value.setBit(typeToIndex("Kaito"));
		return this;
	}
	public TypelineBuilder karn() {
		value = value.setBit(typeToIndex("Karn"));
		return this;
	}
	public TypelineBuilder kasmina() {
		value = value.setBit(typeToIndex("Kasmina"));
		return this;
	}
	public TypelineBuilder kaya() {
		value = value.setBit(typeToIndex("Kaya"));
		return this;
	}
	public TypelineBuilder kiora() {
		value = value.setBit(typeToIndex("Kiora"));
		return this;
	}
	public TypelineBuilder koth() {
		value = value.setBit(typeToIndex("Koth"));
		return this;
	}
	public TypelineBuilder liliana() {
		value = value.setBit(typeToIndex("Liliana"));
		return this;
	}
	public TypelineBuilder lolth() {
		value = value.setBit(typeToIndex("Lolth"));
		return this;
	}
	public TypelineBuilder lukka() {
		value = value.setBit(typeToIndex("Lukka"));
		return this;
	}
	public TypelineBuilder minsc() {
		value = value.setBit(typeToIndex("Minsc"));
		return this;
	}
	public TypelineBuilder mordenkainen() {
		value = value.setBit(typeToIndex("Mordenkainen"));
		return this;
	}
	public TypelineBuilder nahiri() {
		value = value.setBit(typeToIndex("Nahiri"));
		return this;
	}
	public TypelineBuilder narset() {
		value = value.setBit(typeToIndex("Narset"));
		return this;
	}
	public TypelineBuilder niko() {
		value = value.setBit(typeToIndex("Niko"));
		return this;
	}
	public TypelineBuilder nissa() {
		value = value.setBit(typeToIndex("Nissa"));
		return this;
	}
	public TypelineBuilder nixilis() {
		value = value.setBit(typeToIndex("Nixilis"));
		return this;
	}
	public TypelineBuilder oko() {
		value = value.setBit(typeToIndex("Oko"));
		return this;
	}
	public TypelineBuilder quintorius() {
		value = value.setBit(typeToIndex("Quintorius"));
		return this;
	}
	public TypelineBuilder ral() {
		value = value.setBit(typeToIndex("Ral"));
		return this;
	}
	public TypelineBuilder rowan() {
		value = value.setBit(typeToIndex("Rowan"));
		return this;
	}
	public TypelineBuilder saheeli() {
		value = value.setBit(typeToIndex("Saheeli"));
		return this;
	}
	public TypelineBuilder samut() {
		value = value.setBit(typeToIndex("Samut"));
		return this;
	}
	public TypelineBuilder sarkhan() {
		value = value.setBit(typeToIndex("Sarkhan"));
		return this;
	}
	public TypelineBuilder serra() {
		value = value.setBit(typeToIndex("Serra"));
		return this;
	}
	public TypelineBuilder sivitri() {
		value = value.setBit(typeToIndex("Sivitri"));
		return this;
	}
	public TypelineBuilder sorin() {
		value = value.setBit(typeToIndex("Sorin"));
		return this;
	}
	public TypelineBuilder szat() {
		value = value.setBit(typeToIndex("Szat"));
		return this;
	}
	public TypelineBuilder tamiyo() {
		value = value.setBit(typeToIndex("Tamiyo"));
		return this;
	}
	public TypelineBuilder tasha() {
		value = value.setBit(typeToIndex("Tasha"));
		return this;
	}
	public TypelineBuilder teferi() {
		value = value.setBit(typeToIndex("Teferi"));
		return this;
	}
	public TypelineBuilder teyo() {
		value = value.setBit(typeToIndex("Teyo"));
		return this;
	}
	public TypelineBuilder tezzeret() {
		value = value.setBit(typeToIndex("Tezzeret"));
		return this;
	}
	public TypelineBuilder tibalt() {
		value = value.setBit(typeToIndex("Tibalt"));
		return this;
	}
	public TypelineBuilder tyvar() {
		value = value.setBit(typeToIndex("Tyvar"));
		return this;
	}
	public TypelineBuilder ugin() {
		value = value.setBit(typeToIndex("Ugin"));
		return this;
	}
	public TypelineBuilder urza() {
		value = value.setBit(typeToIndex("Urza"));
		return this;
	}
	public TypelineBuilder venser() {
		value = value.setBit(typeToIndex("Venser"));
		return this;
	}
	public TypelineBuilder vivien() {
		value = value.setBit(typeToIndex("Vivien"));
		return this;
	}
	public TypelineBuilder vraska() {
		value = value.setBit(typeToIndex("Vraska"));
		return this;
	}
	public TypelineBuilder vronos() {
		value = value.setBit(typeToIndex("Vronos"));
		return this;
	}
	public TypelineBuilder will() {
		value = value.setBit(typeToIndex("Will"));
		return this;
	}
	public TypelineBuilder windgrace() {
		value = value.setBit(typeToIndex("Windgrace"));
		return this;
	}
	public TypelineBuilder wrenn() {
		value = value.setBit(typeToIndex("Wrenn"));
		return this;
	}
	public TypelineBuilder xenagos() {
		value = value.setBit(typeToIndex("Xenagos"));
		return this;
	}
	public TypelineBuilder yanggu() {
		value = value.setBit(typeToIndex("Yanggu"));
		return this;
	}
	public TypelineBuilder yanling() {
		value = value.setBit(typeToIndex("Yanling"));
		return this;
	}
	public TypelineBuilder zariel() {
		value = value.setBit(typeToIndex("Zariel"));
		return this;
	}
	public TypelineBuilder adventure() {
		value = value.setBit(typeToIndex("Adventure"));
		return this;
	}
	public TypelineBuilder arcane() {
		value = value.setBit(typeToIndex("Arcane"));
		return this;
	}
	public TypelineBuilder lesson() {
		value = value.setBit(typeToIndex("Lesson"));
		return this;
	}
	public TypelineBuilder omen() {
		value = value.setBit(typeToIndex("Omen"));
		return this;
	}
	public TypelineBuilder trap() {
		value = value.setBit(typeToIndex("Trap"));
		return this;
	}
	public TypelineBuilder advisor() {
		value = value.setBit(typeToIndex("Advisor"));
		return this;
	}
	public TypelineBuilder aetherborn() {
		value = value.setBit(typeToIndex("Aetherborn"));
		return this;
	}
	public TypelineBuilder alien() {
		value = value.setBit(typeToIndex("Alien"));
		return this;
	}
	public TypelineBuilder ally() {
		value = value.setBit(typeToIndex("Ally"));
		return this;
	}
	public TypelineBuilder angel() {
		value = value.setBit(typeToIndex("Angel"));
		return this;
	}
	public TypelineBuilder antelope() {
		value = value.setBit(typeToIndex("Antelope"));
		return this;
	}
	public TypelineBuilder ape() {
		value = value.setBit(typeToIndex("Ape"));
		return this;
	}
	public TypelineBuilder archer() {
		value = value.setBit(typeToIndex("Archer"));
		return this;
	}
	public TypelineBuilder archon() {
		value = value.setBit(typeToIndex("Archon"));
		return this;
	}
	public TypelineBuilder armadillo() {
		value = value.setBit(typeToIndex("Armadillo"));
		return this;
	}
	public TypelineBuilder army() {
		value = value.setBit(typeToIndex("Army"));
		return this;
	}
	public TypelineBuilder artificer() {
		value = value.setBit(typeToIndex("Artificer"));
		return this;
	}
	public TypelineBuilder assassin() {
		value = value.setBit(typeToIndex("Assassin"));
		return this;
	}
	public TypelineBuilder assembly_worker() {
		value = value.setBit(typeToIndex("Assembly_Worker"));
		return this;
	}
	public TypelineBuilder astartes() {
		value = value.setBit(typeToIndex("Astartes"));
		return this;
	}
	public TypelineBuilder atog() {
		value = value.setBit(typeToIndex("Atog"));
		return this;
	}
	public TypelineBuilder aurochs() {
		value = value.setBit(typeToIndex("Aurochs"));
		return this;
	}
	public TypelineBuilder avatar() {
		value = value.setBit(typeToIndex("Avatar"));
		return this;
	}
	public TypelineBuilder azra() {
		value = value.setBit(typeToIndex("Azra"));
		return this;
	}
	public TypelineBuilder badger() {
		value = value.setBit(typeToIndex("Badger"));
		return this;
	}
	public TypelineBuilder balloon() {
		value = value.setBit(typeToIndex("Balloon"));
		return this;
	}
	public TypelineBuilder barbarian() {
		value = value.setBit(typeToIndex("Barbarian"));
		return this;
	}
	public TypelineBuilder bard() {
		value = value.setBit(typeToIndex("Bard"));
		return this;
	}
	public TypelineBuilder basilisk() {
		value = value.setBit(typeToIndex("Basilisk"));
		return this;
	}
	public TypelineBuilder bat() {
		value = value.setBit(typeToIndex("Bat"));
		return this;
	}
	public TypelineBuilder bear() {
		value = value.setBit(typeToIndex("Bear"));
		return this;
	}
	public TypelineBuilder beast() {
		value = value.setBit(typeToIndex("Beast"));
		return this;
	}
	public TypelineBuilder beaver() {
		value = value.setBit(typeToIndex("Beaver"));
		return this;
	}
	public TypelineBuilder beeble() {
		value = value.setBit(typeToIndex("Beeble"));
		return this;
	}
	public TypelineBuilder beholder() {
		value = value.setBit(typeToIndex("Beholder"));
		return this;
	}
	public TypelineBuilder berserker() {
		value = value.setBit(typeToIndex("Berserker"));
		return this;
	}
	public TypelineBuilder bird() {
		value = value.setBit(typeToIndex("Bird"));
		return this;
	}
	public TypelineBuilder blinkmoth() {
		value = value.setBit(typeToIndex("Blinkmoth"));
		return this;
	}
	public TypelineBuilder boar() {
		value = value.setBit(typeToIndex("Boar"));
		return this;
	}
	public TypelineBuilder bringer() {
		value = value.setBit(typeToIndex("Bringer"));
		return this;
	}
	public TypelineBuilder brushwagg() {
		value = value.setBit(typeToIndex("Brushwagg"));
		return this;
	}
	public TypelineBuilder camarid() {
		value = value.setBit(typeToIndex("Camarid"));
		return this;
	}
	public TypelineBuilder camel() {
		value = value.setBit(typeToIndex("Camel"));
		return this;
	}
	public TypelineBuilder capybara() {
		value = value.setBit(typeToIndex("Capybara"));
		return this;
	}
	public TypelineBuilder caribou() {
		value = value.setBit(typeToIndex("Caribou"));
		return this;
	}
	public TypelineBuilder carrier() {
		value = value.setBit(typeToIndex("Carrier"));
		return this;
	}
	public TypelineBuilder cat() {
		value = value.setBit(typeToIndex("Cat"));
		return this;
	}
	public TypelineBuilder centaur() {
		value = value.setBit(typeToIndex("Centaur"));
		return this;
	}
	public TypelineBuilder child() {
		value = value.setBit(typeToIndex("Child"));
		return this;
	}
	public TypelineBuilder chimera() {
		value = value.setBit(typeToIndex("Chimera"));
		return this;
	}
	public TypelineBuilder citizen() {
		value = value.setBit(typeToIndex("Citizen"));
		return this;
	}
	public TypelineBuilder cleric() {
		value = value.setBit(typeToIndex("Cleric"));
		return this;
	}
	public TypelineBuilder clown() {
		value = value.setBit(typeToIndex("Clown"));
		return this;
	}
	public TypelineBuilder cockatrice() {
		value = value.setBit(typeToIndex("Cockatrice"));
		return this;
	}
	public TypelineBuilder construct() {
		value = value.setBit(typeToIndex("Construct"));
		return this;
	}
	public TypelineBuilder coward() {
		value = value.setBit(typeToIndex("Coward"));
		return this;
	}
	public TypelineBuilder coyote() {
		value = value.setBit(typeToIndex("Coyote"));
		return this;
	}
	public TypelineBuilder crab() {
		value = value.setBit(typeToIndex("Crab"));
		return this;
	}
	public TypelineBuilder crocodile() {
		value = value.setBit(typeToIndex("Crocodile"));
		return this;
	}
	public TypelineBuilder c_tan() {
		value = value.setBit(typeToIndex("C_tan"));
		return this;
	}
	public TypelineBuilder custodes() {
		value = value.setBit(typeToIndex("Custodes"));
		return this;
	}
	public TypelineBuilder cyberman() {
		value = value.setBit(typeToIndex("Cyberman"));
		return this;
	}
	public TypelineBuilder cyclops() {
		value = value.setBit(typeToIndex("Cyclops"));
		return this;
	}
	public TypelineBuilder dalek() {
		value = value.setBit(typeToIndex("Dalek"));
		return this;
	}
	public TypelineBuilder dauthi() {
		value = value.setBit(typeToIndex("Dauthi"));
		return this;
	}
	public TypelineBuilder demigod() {
		value = value.setBit(typeToIndex("Demigod"));
		return this;
	}
	public TypelineBuilder demon() {
		value = value.setBit(typeToIndex("Demon"));
		return this;
	}
	public TypelineBuilder deserter() {
		value = value.setBit(typeToIndex("Deserter"));
		return this;
	}
	public TypelineBuilder detective() {
		value = value.setBit(typeToIndex("Detective"));
		return this;
	}
	public TypelineBuilder devil() {
		value = value.setBit(typeToIndex("Devil"));
		return this;
	}
	public TypelineBuilder dinosaur() {
		value = value.setBit(typeToIndex("Dinosaur"));
		return this;
	}
	public TypelineBuilder djinn() {
		value = value.setBit(typeToIndex("Djinn"));
		return this;
	}
	public TypelineBuilder doctor() {
		value = value.setBit(typeToIndex("Doctor"));
		return this;
	}
	public TypelineBuilder dog() {
		value = value.setBit(typeToIndex("Dog"));
		return this;
	}
	public TypelineBuilder dragon() {
		value = value.setBit(typeToIndex("Dragon"));
		return this;
	}
	public TypelineBuilder drake() {
		value = value.setBit(typeToIndex("Drake"));
		return this;
	}
	public TypelineBuilder dreadnought() {
		value = value.setBit(typeToIndex("Dreadnought"));
		return this;
	}
	public TypelineBuilder drone() {
		value = value.setBit(typeToIndex("Drone"));
		return this;
	}
	public TypelineBuilder druid() {
		value = value.setBit(typeToIndex("Druid"));
		return this;
	}
	public TypelineBuilder dryad() {
		value = value.setBit(typeToIndex("Dryad"));
		return this;
	}
	public TypelineBuilder dwarf() {
		value = value.setBit(typeToIndex("Dwarf"));
		return this;
	}
	public TypelineBuilder efreet() {
		value = value.setBit(typeToIndex("Efreet"));
		return this;
	}
	public TypelineBuilder egg() {
		value = value.setBit(typeToIndex("Egg"));
		return this;
	}
	public TypelineBuilder elder() {
		value = value.setBit(typeToIndex("Elder"));
		return this;
	}
	public TypelineBuilder eldrazi() {
		value = value.setBit(typeToIndex("Eldrazi"));
		return this;
	}
	public TypelineBuilder elemental() {
		value = value.setBit(typeToIndex("Elemental"));
		return this;
	}
	public TypelineBuilder elephant() {
		value = value.setBit(typeToIndex("Elephant"));
		return this;
	}
	public TypelineBuilder elf() {
		value = value.setBit(typeToIndex("Elf"));
		return this;
	}
	public TypelineBuilder elk() {
		value = value.setBit(typeToIndex("Elk"));
		return this;
	}
	public TypelineBuilder employee() {
		value = value.setBit(typeToIndex("Employee"));
		return this;
	}
	public TypelineBuilder eye() {
		value = value.setBit(typeToIndex("Eye"));
		return this;
	}
	public TypelineBuilder faerie() {
		value = value.setBit(typeToIndex("Faerie"));
		return this;
	}
	public TypelineBuilder ferret() {
		value = value.setBit(typeToIndex("Ferret"));
		return this;
	}
	public TypelineBuilder fish() {
		value = value.setBit(typeToIndex("Fish"));
		return this;
	}
	public TypelineBuilder flagbearer() {
		value = value.setBit(typeToIndex("Flagbearer"));
		return this;
	}
	public TypelineBuilder fox() {
		value = value.setBit(typeToIndex("Fox"));
		return this;
	}
	public TypelineBuilder fractal() {
		value = value.setBit(typeToIndex("Fractal"));
		return this;
	}
	public TypelineBuilder frog() {
		value = value.setBit(typeToIndex("Frog"));
		return this;
	}
	public TypelineBuilder fungus() {
		value = value.setBit(typeToIndex("Fungus"));
		return this;
	}
	public TypelineBuilder gamer() {
		value = value.setBit(typeToIndex("Gamer"));
		return this;
	}
	public TypelineBuilder gargoyle() {
		value = value.setBit(typeToIndex("Gargoyle"));
		return this;
	}
	public TypelineBuilder germ() {
		value = value.setBit(typeToIndex("Germ"));
		return this;
	}
	public TypelineBuilder giant() {
		value = value.setBit(typeToIndex("Giant"));
		return this;
	}
	public TypelineBuilder gith() {
		value = value.setBit(typeToIndex("Gith"));
		return this;
	}
	public TypelineBuilder glimmer() {
		value = value.setBit(typeToIndex("Glimmer"));
		return this;
	}
	public TypelineBuilder gnoll() {
		value = value.setBit(typeToIndex("Gnoll"));
		return this;
	}
	public TypelineBuilder gnome() {
		value = value.setBit(typeToIndex("Gnome"));
		return this;
	}
	public TypelineBuilder goat() {
		value = value.setBit(typeToIndex("Goat"));
		return this;
	}
	public TypelineBuilder goblin() {
		value = value.setBit(typeToIndex("Goblin"));
		return this;
	}
	public TypelineBuilder god() {
		value = value.setBit(typeToIndex("God"));
		return this;
	}
	public TypelineBuilder golem() {
		value = value.setBit(typeToIndex("Golem"));
		return this;
	}
	public TypelineBuilder gorgon() {
		value = value.setBit(typeToIndex("Gorgon"));
		return this;
	}
	public TypelineBuilder graveborn() {
		value = value.setBit(typeToIndex("Graveborn"));
		return this;
	}
	public TypelineBuilder gremlin() {
		value = value.setBit(typeToIndex("Gremlin"));
		return this;
	}
	public TypelineBuilder griffin() {
		value = value.setBit(typeToIndex("Griffin"));
		return this;
	}
	public TypelineBuilder guest() {
		value = value.setBit(typeToIndex("Guest"));
		return this;
	}
	public TypelineBuilder hag() {
		value = value.setBit(typeToIndex("Hag"));
		return this;
	}
	public TypelineBuilder halfling() {
		value = value.setBit(typeToIndex("Halfling"));
		return this;
	}
	public TypelineBuilder hamster() {
		value = value.setBit(typeToIndex("Hamster"));
		return this;
	}
	public TypelineBuilder harpy() {
		value = value.setBit(typeToIndex("Harpy"));
		return this;
	}
	public TypelineBuilder hellion() {
		value = value.setBit(typeToIndex("Hellion"));
		return this;
	}
	public TypelineBuilder hippo() {
		value = value.setBit(typeToIndex("Hippo"));
		return this;
	}
	public TypelineBuilder hippogriff() {
		value = value.setBit(typeToIndex("Hippogriff"));
		return this;
	}
	public TypelineBuilder homarid() {
		value = value.setBit(typeToIndex("Homarid"));
		return this;
	}
	public TypelineBuilder homunculus() {
		value = value.setBit(typeToIndex("Homunculus"));
		return this;
	}
	public TypelineBuilder horror() {
		value = value.setBit(typeToIndex("Horror"));
		return this;
	}
	public TypelineBuilder horse() {
		value = value.setBit(typeToIndex("Horse"));
		return this;
	}
	public TypelineBuilder human() {
		value = value.setBit(typeToIndex("Human"));
		return this;
	}
	public TypelineBuilder hydra() {
		value = value.setBit(typeToIndex("Hydra"));
		return this;
	}
	public TypelineBuilder hyena() {
		value = value.setBit(typeToIndex("Hyena"));
		return this;
	}
	public TypelineBuilder illusion() {
		value = value.setBit(typeToIndex("Illusion"));
		return this;
	}
	public TypelineBuilder imp() {
		value = value.setBit(typeToIndex("Imp"));
		return this;
	}
	public TypelineBuilder incarnation() {
		value = value.setBit(typeToIndex("Incarnation"));
		return this;
	}
	public TypelineBuilder inkling() {
		value = value.setBit(typeToIndex("Inkling"));
		return this;
	}
	public TypelineBuilder inquisitor() {
		value = value.setBit(typeToIndex("Inquisitor"));
		return this;
	}
	public TypelineBuilder insect() {
		value = value.setBit(typeToIndex("Insect"));
		return this;
	}
	public TypelineBuilder jackal() {
		value = value.setBit(typeToIndex("Jackal"));
		return this;
	}
	public TypelineBuilder jellyfish() {
		value = value.setBit(typeToIndex("Jellyfish"));
		return this;
	}
	public TypelineBuilder juggernaut() {
		value = value.setBit(typeToIndex("Juggernaut"));
		return this;
	}
	public TypelineBuilder kavu() {
		value = value.setBit(typeToIndex("Kavu"));
		return this;
	}
	public TypelineBuilder kirin() {
		value = value.setBit(typeToIndex("Kirin"));
		return this;
	}
	public TypelineBuilder kithkin() {
		value = value.setBit(typeToIndex("Kithkin"));
		return this;
	}
	public TypelineBuilder knight() {
		value = value.setBit(typeToIndex("Knight"));
		return this;
	}
	public TypelineBuilder kobold() {
		value = value.setBit(typeToIndex("Kobold"));
		return this;
	}
	public TypelineBuilder kor() {
		value = value.setBit(typeToIndex("Kor"));
		return this;
	}
	public TypelineBuilder kraken() {
		value = value.setBit(typeToIndex("Kraken"));
		return this;
	}
	public TypelineBuilder llama() {
		value = value.setBit(typeToIndex("Llama"));
		return this;
	}
	public TypelineBuilder lamia() {
		value = value.setBit(typeToIndex("Lamia"));
		return this;
	}
	public TypelineBuilder lammasu() {
		value = value.setBit(typeToIndex("Lammasu"));
		return this;
	}
	public TypelineBuilder leech() {
		value = value.setBit(typeToIndex("Leech"));
		return this;
	}
	public TypelineBuilder leviathan() {
		value = value.setBit(typeToIndex("Leviathan"));
		return this;
	}
	public TypelineBuilder lhurgoyf() {
		value = value.setBit(typeToIndex("Lhurgoyf"));
		return this;
	}
	public TypelineBuilder licid() {
		value = value.setBit(typeToIndex("Licid"));
		return this;
	}
	public TypelineBuilder lizard() {
		value = value.setBit(typeToIndex("Lizard"));
		return this;
	}
	public TypelineBuilder manticore() {
		value = value.setBit(typeToIndex("Manticore"));
		return this;
	}
	public TypelineBuilder masticore() {
		value = value.setBit(typeToIndex("Masticore"));
		return this;
	}
	public TypelineBuilder mercenary() {
		value = value.setBit(typeToIndex("Mercenary"));
		return this;
	}
	public TypelineBuilder merfolk() {
		value = value.setBit(typeToIndex("Merfolk"));
		return this;
	}
	public TypelineBuilder metathran() {
		value = value.setBit(typeToIndex("Metathran"));
		return this;
	}
	public TypelineBuilder minion() {
		value = value.setBit(typeToIndex("Minion"));
		return this;
	}
	public TypelineBuilder minotaur() {
		value = value.setBit(typeToIndex("Minotaur"));
		return this;
	}
	public TypelineBuilder mite() {
		value = value.setBit(typeToIndex("Mite"));
		return this;
	}
	public TypelineBuilder mole() {
		value = value.setBit(typeToIndex("Mole"));
		return this;
	}
	public TypelineBuilder monger() {
		value = value.setBit(typeToIndex("Monger"));
		return this;
	}
	public TypelineBuilder mongoose() {
		value = value.setBit(typeToIndex("Mongoose"));
		return this;
	}
	public TypelineBuilder monk() {
		value = value.setBit(typeToIndex("Monk"));
		return this;
	}
	public TypelineBuilder monkey() {
		value = value.setBit(typeToIndex("Monkey"));
		return this;
	}
	public TypelineBuilder moonfolk() {
		value = value.setBit(typeToIndex("Moonfolk"));
		return this;
	}
	public TypelineBuilder mount() {
		value = value.setBit(typeToIndex("Mount"));
		return this;
	}
	public TypelineBuilder mouse() {
		value = value.setBit(typeToIndex("Mouse"));
		return this;
	}
	public TypelineBuilder mutant() {
		value = value.setBit(typeToIndex("Mutant"));
		return this;
	}
	public TypelineBuilder myr() {
		value = value.setBit(typeToIndex("Myr"));
		return this;
	}
	public TypelineBuilder mystic() {
		value = value.setBit(typeToIndex("Mystic"));
		return this;
	}
	public TypelineBuilder nautilus() {
		value = value.setBit(typeToIndex("Nautilus"));
		return this;
	}
	public TypelineBuilder necron() {
		value = value.setBit(typeToIndex("Necron"));
		return this;
	}
	public TypelineBuilder nephilim() {
		value = value.setBit(typeToIndex("Nephilim"));
		return this;
	}
	public TypelineBuilder nightmare() {
		value = value.setBit(typeToIndex("Nightmare"));
		return this;
	}
	public TypelineBuilder nightstalker() {
		value = value.setBit(typeToIndex("Nightstalker"));
		return this;
	}
	public TypelineBuilder ninja() {
		value = value.setBit(typeToIndex("Ninja"));
		return this;
	}
	public TypelineBuilder noble() {
		value = value.setBit(typeToIndex("Noble"));
		return this;
	}
	public TypelineBuilder noggle() {
		value = value.setBit(typeToIndex("Noggle"));
		return this;
	}
	public TypelineBuilder nomad() {
		value = value.setBit(typeToIndex("Nomad"));
		return this;
	}
	public TypelineBuilder nymph() {
		value = value.setBit(typeToIndex("Nymph"));
		return this;
	}
	public TypelineBuilder octopus() {
		value = value.setBit(typeToIndex("Octopus"));
		return this;
	}
	public TypelineBuilder ogre() {
		value = value.setBit(typeToIndex("Ogre"));
		return this;
	}
	public TypelineBuilder ooze() {
		value = value.setBit(typeToIndex("Ooze"));
		return this;
	}
	public TypelineBuilder orb() {
		value = value.setBit(typeToIndex("Orb"));
		return this;
	}
	public TypelineBuilder orc() {
		value = value.setBit(typeToIndex("Orc"));
		return this;
	}
	public TypelineBuilder orgg() {
		value = value.setBit(typeToIndex("Orgg"));
		return this;
	}
	public TypelineBuilder otter() {
		value = value.setBit(typeToIndex("Otter"));
		return this;
	}
	public TypelineBuilder ouphe() {
		value = value.setBit(typeToIndex("Ouphe"));
		return this;
	}
	public TypelineBuilder ox() {
		value = value.setBit(typeToIndex("Ox"));
		return this;
	}
	public TypelineBuilder oyster() {
		value = value.setBit(typeToIndex("Oyster"));
		return this;
	}
	public TypelineBuilder pangolin() {
		value = value.setBit(typeToIndex("Pangolin"));
		return this;
	}
	public TypelineBuilder peasant() {
		value = value.setBit(typeToIndex("Peasant"));
		return this;
	}
	public TypelineBuilder pegasus() {
		value = value.setBit(typeToIndex("Pegasus"));
		return this;
	}
	public TypelineBuilder pentavite() {
		value = value.setBit(typeToIndex("Pentavite"));
		return this;
	}
	public TypelineBuilder performer() {
		value = value.setBit(typeToIndex("Performer"));
		return this;
	}
	public TypelineBuilder pest() {
		value = value.setBit(typeToIndex("Pest"));
		return this;
	}
	public TypelineBuilder phelddagrif() {
		value = value.setBit(typeToIndex("Phelddagrif"));
		return this;
	}
	public TypelineBuilder phoenix() {
		value = value.setBit(typeToIndex("Phoenix"));
		return this;
	}
	public TypelineBuilder phyrexian() {
		value = value.setBit(typeToIndex("Phyrexian"));
		return this;
	}
	public TypelineBuilder pilot() {
		value = value.setBit(typeToIndex("Pilot"));
		return this;
	}
	public TypelineBuilder pincher() {
		value = value.setBit(typeToIndex("Pincher"));
		return this;
	}
	public TypelineBuilder pirate() {
		value = value.setBit(typeToIndex("Pirate"));
		return this;
	}
	public TypelineBuilder plant() {
		value = value.setBit(typeToIndex("Plant"));
		return this;
	}
	public TypelineBuilder porcupine() {
		value = value.setBit(typeToIndex("Porcupine"));
		return this;
	}
	public TypelineBuilder possum() {
		value = value.setBit(typeToIndex("Possum"));
		return this;
	}
	public TypelineBuilder praetor() {
		value = value.setBit(typeToIndex("Praetor"));
		return this;
	}
	public TypelineBuilder primarch() {
		value = value.setBit(typeToIndex("Primarch"));
		return this;
	}
	public TypelineBuilder prism() {
		value = value.setBit(typeToIndex("Prism"));
		return this;
	}
	public TypelineBuilder processor() {
		value = value.setBit(typeToIndex("Processor"));
		return this;
	}
	public TypelineBuilder rabbit() {
		value = value.setBit(typeToIndex("Rabbit"));
		return this;
	}
	public TypelineBuilder raccoon() {
		value = value.setBit(typeToIndex("Raccoon"));
		return this;
	}
	public TypelineBuilder ranger() {
		value = value.setBit(typeToIndex("Ranger"));
		return this;
	}
	public TypelineBuilder rat() {
		value = value.setBit(typeToIndex("Rat"));
		return this;
	}
	public TypelineBuilder rebel() {
		value = value.setBit(typeToIndex("Rebel"));
		return this;
	}
	public TypelineBuilder reflection() {
		value = value.setBit(typeToIndex("Reflection"));
		return this;
	}
	public TypelineBuilder rhino() {
		value = value.setBit(typeToIndex("Rhino"));
		return this;
	}
	public TypelineBuilder rigger() {
		value = value.setBit(typeToIndex("Rigger"));
		return this;
	}
	public TypelineBuilder robot() {
		value = value.setBit(typeToIndex("Robot"));
		return this;
	}
	public TypelineBuilder rogue() {
		value = value.setBit(typeToIndex("Rogue"));
		return this;
	}
	public TypelineBuilder sable() {
		value = value.setBit(typeToIndex("Sable"));
		return this;
	}
	public TypelineBuilder salamander() {
		value = value.setBit(typeToIndex("Salamander"));
		return this;
	}
	public TypelineBuilder samurai() {
		value = value.setBit(typeToIndex("Samurai"));
		return this;
	}
	public TypelineBuilder sand() {
		value = value.setBit(typeToIndex("Sand"));
		return this;
	}
	public TypelineBuilder saproling() {
		value = value.setBit(typeToIndex("Saproling"));
		return this;
	}
	public TypelineBuilder satyr() {
		value = value.setBit(typeToIndex("Satyr"));
		return this;
	}
	public TypelineBuilder scarecrow() {
		value = value.setBit(typeToIndex("Scarecrow"));
		return this;
	}
	public TypelineBuilder scientist() {
		value = value.setBit(typeToIndex("Scientist"));
		return this;
	}
	public TypelineBuilder scion() {
		value = value.setBit(typeToIndex("Scion"));
		return this;
	}
	public TypelineBuilder scorpion() {
		value = value.setBit(typeToIndex("Scorpion"));
		return this;
	}
	public TypelineBuilder scout() {
		value = value.setBit(typeToIndex("Scout"));
		return this;
	}
	public TypelineBuilder sculpture() {
		value = value.setBit(typeToIndex("Sculpture"));
		return this;
	}
	public TypelineBuilder seal() {
		value = value.setBit(typeToIndex("Seal"));
		return this;
	}
	public TypelineBuilder serf() {
		value = value.setBit(typeToIndex("Serf"));
		return this;
	}
	public TypelineBuilder serpent() {
		value = value.setBit(typeToIndex("Serpent"));
		return this;
	}
	public TypelineBuilder servo() {
		value = value.setBit(typeToIndex("Servo"));
		return this;
	}
	public TypelineBuilder shade() {
		value = value.setBit(typeToIndex("Shade"));
		return this;
	}
	public TypelineBuilder shaman() {
		value = value.setBit(typeToIndex("Shaman"));
		return this;
	}
	public TypelineBuilder shapeshifter() {
		value = value.setBit(typeToIndex("Shapeshifter"));
		return this;
	}
	public TypelineBuilder shark() {
		value = value.setBit(typeToIndex("Shark"));
		return this;
	}
	public TypelineBuilder sheep() {
		value = value.setBit(typeToIndex("Sheep"));
		return this;
	}
	public TypelineBuilder siren() {
		value = value.setBit(typeToIndex("Siren"));
		return this;
	}
	public TypelineBuilder skeleton() {
		value = value.setBit(typeToIndex("Skeleton"));
		return this;
	}
	public TypelineBuilder skunk() {
		value = value.setBit(typeToIndex("Skunk"));
		return this;
	}
	public TypelineBuilder slith() {
		value = value.setBit(typeToIndex("Slith"));
		return this;
	}
	public TypelineBuilder sliver() {
		value = value.setBit(typeToIndex("Sliver"));
		return this;
	}
	public TypelineBuilder sloth() {
		value = value.setBit(typeToIndex("Sloth"));
		return this;
	}
	public TypelineBuilder slug() {
		value = value.setBit(typeToIndex("Slug"));
		return this;
	}
	public TypelineBuilder snail() {
		value = value.setBit(typeToIndex("Snail"));
		return this;
	}
	public TypelineBuilder snake() {
		value = value.setBit(typeToIndex("Snake"));
		return this;
	}
	public TypelineBuilder soldier() {
		value = value.setBit(typeToIndex("Soldier"));
		return this;
	}
	public TypelineBuilder soltari() {
		value = value.setBit(typeToIndex("Soltari"));
		return this;
	}
	public TypelineBuilder spawn() {
		value = value.setBit(typeToIndex("Spawn"));
		return this;
	}
	public TypelineBuilder specter() {
		value = value.setBit(typeToIndex("Specter"));
		return this;
	}
	public TypelineBuilder spellshaper() {
		value = value.setBit(typeToIndex("Spellshaper"));
		return this;
	}
	public TypelineBuilder sphinx() {
		value = value.setBit(typeToIndex("Sphinx"));
		return this;
	}
	public TypelineBuilder spider() {
		value = value.setBit(typeToIndex("Spider"));
		return this;
	}
	public TypelineBuilder spike() {
		value = value.setBit(typeToIndex("Spike"));
		return this;
	}
	public TypelineBuilder spirit() {
		value = value.setBit(typeToIndex("Spirit"));
		return this;
	}
	public TypelineBuilder splinter() {
		value = value.setBit(typeToIndex("Splinter"));
		return this;
	}
	public TypelineBuilder sponge() {
		value = value.setBit(typeToIndex("Sponge"));
		return this;
	}
	public TypelineBuilder squid() {
		value = value.setBit(typeToIndex("Squid"));
		return this;
	}
	public TypelineBuilder squirrel() {
		value = value.setBit(typeToIndex("Squirrel"));
		return this;
	}
	public TypelineBuilder starfish() {
		value = value.setBit(typeToIndex("Starfish"));
		return this;
	}
	public TypelineBuilder surrakar() {
		value = value.setBit(typeToIndex("Surrakar"));
		return this;
	}
	public TypelineBuilder survivor() {
		value = value.setBit(typeToIndex("Survivor"));
		return this;
	}
	public TypelineBuilder synth() {
		value = value.setBit(typeToIndex("Synth"));
		return this;
	}
	public TypelineBuilder tentacle() {
		value = value.setBit(typeToIndex("Tentacle"));
		return this;
	}
	public TypelineBuilder tetravite() {
		value = value.setBit(typeToIndex("Tetravite"));
		return this;
	}
	public TypelineBuilder thalakos() {
		value = value.setBit(typeToIndex("Thalakos"));
		return this;
	}
	public TypelineBuilder thopter() {
		value = value.setBit(typeToIndex("Thopter"));
		return this;
	}
	public TypelineBuilder thrull() {
		value = value.setBit(typeToIndex("Thrull"));
		return this;
	}
	public TypelineBuilder tiefling() {
		value = value.setBit(typeToIndex("Tiefling"));
		return this;
	}
	public TypelineBuilder time_lord() {
		value = value.setBit(typeToIndex("Time_Lord"));
		return this;
	}
	public TypelineBuilder toy() {
		value = value.setBit(typeToIndex("Toy"));
		return this;
	}
	public TypelineBuilder treefolk() {
		value = value.setBit(typeToIndex("Treefolk"));
		return this;
	}
	public TypelineBuilder trilobite() {
		value = value.setBit(typeToIndex("Trilobite"));
		return this;
	}
	public TypelineBuilder triskelavite() {
		value = value.setBit(typeToIndex("Triskelavite"));
		return this;
	}
	public TypelineBuilder troll() {
		value = value.setBit(typeToIndex("Troll"));
		return this;
	}
	public TypelineBuilder turtle() {
		value = value.setBit(typeToIndex("Turtle"));
		return this;
	}
	public TypelineBuilder tyranid() {
		value = value.setBit(typeToIndex("Tyranid"));
		return this;
	}
	public TypelineBuilder unicorn() {
		value = value.setBit(typeToIndex("Unicorn"));
		return this;
	}
	public TypelineBuilder vampire() {
		value = value.setBit(typeToIndex("Vampire"));
		return this;
	}
	public TypelineBuilder varmint() {
		value = value.setBit(typeToIndex("Varmint"));
		return this;
	}
	public TypelineBuilder vedalken() {
		value = value.setBit(typeToIndex("Vedalken"));
		return this;
	}
	public TypelineBuilder volver() {
		value = value.setBit(typeToIndex("Volver"));
		return this;
	}
	public TypelineBuilder wall() {
		value = value.setBit(typeToIndex("Wall"));
		return this;
	}
	public TypelineBuilder walrus() {
		value = value.setBit(typeToIndex("Walrus"));
		return this;
	}
	public TypelineBuilder warlock() {
		value = value.setBit(typeToIndex("Warlock"));
		return this;
	}
	public TypelineBuilder warrior() {
		value = value.setBit(typeToIndex("Warrior"));
		return this;
	}
	public TypelineBuilder weasel() {
		value = value.setBit(typeToIndex("Weasel"));
		return this;
	}
	public TypelineBuilder weird() {
		value = value.setBit(typeToIndex("Weird"));
		return this;
	}
	public TypelineBuilder werewolf() {
		value = value.setBit(typeToIndex("Werewolf"));
		return this;
	}
	public TypelineBuilder whale() {
		value = value.setBit(typeToIndex("Whale"));
		return this;
	}
	public TypelineBuilder wizard() {
		value = value.setBit(typeToIndex("Wizard"));
		return this;
	}
	public TypelineBuilder wolf() {
		value = value.setBit(typeToIndex("Wolf"));
		return this;
	}
	public TypelineBuilder wolverine() {
		value = value.setBit(typeToIndex("Wolverine"));
		return this;
	}
	public TypelineBuilder wombat() {
		value = value.setBit(typeToIndex("Wombat"));
		return this;
	}
	public TypelineBuilder worm() {
		value = value.setBit(typeToIndex("Worm"));
		return this;
	}
	public TypelineBuilder wraith() {
		value = value.setBit(typeToIndex("Wraith"));
		return this;
	}
	public TypelineBuilder wurm() {
		value = value.setBit(typeToIndex("Wurm"));
		return this;
	}
	public TypelineBuilder yeti() {
		value = value.setBit(typeToIndex("Yeti"));
		return this;
	}
	public TypelineBuilder zombie() {
		value = value.setBit(typeToIndex("Zombie"));
		return this;
	}
	public TypelineBuilder zubera() {
		value = value.setBit(typeToIndex("Zubera"));
		return this;
	}
	public TypelineBuilder siege() {
		value = value.setBit(typeToIndex("Siege"));
		return this;
	}

	public TypelineBuilder add(String type) {
		value = value.setBit(typeToIndex(type));
		return this;
	}

	public Typeline build() {
		return new Typeline(value);
	}
}