#FitnessClub

### Tekst zadatka: 

Smoren od programiranja hoces da se bezis u svet fitness-a. Ali avaj
da bi pobegao moras ipak prethodno da isprogramis softver za vodjenje fitnes kluba.

Nista grandiozno, elementarno sve.
Clan kluba ima ime, prezime i broj clanske karte.
Fitnes klub ima kapacitet 16 posetilaca odjednom, vreme koliko ce clan da se zadrzi
ograniceno je na max 3 sata. Klub radi 12 sati dnevno.

Tvoj "softver" treba da omoguci:

a) da izbaci spisak clanova koji su tu trenutno.
b) da izbaci spisak clanova koji ce danas biti tu
c) da ima pretragu po imenu i/ili prezimenu da se dobije ID clana
d1) da kada clan pozove i hoce da zakaze da odredi
da li uopste danas moze da dodje (da li ima slobodnih perioda) i koliko max moze da ostane.
d2) kada se clanu kaze koliko max moze da ostane, on moze da
zakaze i kraci termin od toga.

Tvoj projekat treba da bude organizovan u module koristeci Maven.
Arhitektura treba da bude viseslojna.
Jezgro aplikacije je najvazniji sloj i treba da bude sto je nezavisniji moguce od ostalih slojeva.
Oko njega treba da se uvezu ostali.
Modul za korisnicki interfejs (obicna konzolna aplikacija - unos preko tastature, tekstualni, izlaz isto - tekstualni).
Treba da imas sloj za "cuvanje" podataka, cuvanje u memoriji je sad, zasad, dovoljno. Kasnije - videcemo,
ako bude vremena ubacicemo bazu podataka.

Nema potrebe da se unit testovima pokriva svaka sitnica, kljucne funkcionalnosti moraju biti pokrivene 
unit testovima.