# Version-Control-System
Implemented an elementary set of unix and vcs commands in Java.

  In implementarea comenzilor de VCS, am creat in pachetul "vcs" cate
o clasa (ce extinde VcsOperation) corespunzatoare fiecareia (in 
StatusOperation implementez vcs status, in BranchOperation implementez vcs 
branch etc.).

  Pentru a putea lucra cu notiunile de commit si branch, am realizat
cate o clasa si pentru acestea. Commit contine campurile id, message, 
versiunea fisierelor din sistem si un boolean head (este true daca cursorul
se afla pe commit-ul respectiv). Clasa Branch contine campul name si o 
lista cu toate commit-urile existente pe branch-ul respectiv. Branch-ul
master (impreuna cu primul commit) sunt initializate in clasa Vcs.

  Notiunea de staging o integrez in clasa Context ca o lista de elemente
de tip String, in care salvez operatiile trackable de filesystem ce au fost
efectuate pana sa facem vcs commit.

  La fiecare operatie de vcs, pe langa implementarea efectiva, am verificat 
daca aceasta a fost introdusa in mod corect, altfel returnez codul de eroare 
specific.
