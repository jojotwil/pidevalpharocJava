package Entities;


import java.util.Date;
public class PostTroc {
        private int id;

        private Date annee;
        private double kilometrage;
        private String description;
        private String localisation;
        private String image;
        private String mail;
        private String matricule;
        private String marque;
        private String modele;
        private String typecarburant;
        private String categorievehicule;
        private String typeboitevitesse;

        //constructeur sans paramétres


        public PostTroc() {}

        //constructeur avec paramétres


        public PostTroc(Date annee, double kilometrage, String description, String localisation, String image, String mail, String matricule, String marque, String modele, String typecarburant, String categorievehicule, String typeboitevitesse) {
            this.annee = annee;
            this.kilometrage = kilometrage;
            this.description = description;
            this.localisation = localisation;
            this.image = image;
            this.mail = mail;
            this.matricule = matricule;
            this.marque = marque;
            this.modele = modele;
            this.typecarburant = typecarburant;
            this.categorievehicule = categorievehicule;
            this.typeboitevitesse = typeboitevitesse;
        }

    public PostTroc(int id, Date annee, double kilometrage, String description, String localisation, String image, String mail, String matricule, String marque, String modele, String typecarburant, String categorievehicule, String typeboitevitesse) {
        this.id = id;
        this.annee = annee;
        this.kilometrage = kilometrage;
        this.description = description;
        this.localisation = localisation;
        this.image = image;
        this.mail = mail;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.typecarburant = typecarburant;
        this.categorievehicule = categorievehicule;
        this.typeboitevitesse = typeboitevitesse;
    }
//geters

        public int getId() {
            return id;
        }



        public Date getAnnee() {
            return annee;
        }

        public double getKilometrage() {
            return kilometrage;
        }

        public String getDescription() {
            return description;
        }

        public String getLocalisation() {
            return localisation;
        }

        public String getImage() {
            return image;
        }

        public String getMail() {
            return mail;
        }

        public String getMatricule() {
            return matricule;
        }

        public String getMarque() {
            return marque;
        }

        public String getModele() {
            return modele;
        }

        public String getTypecarburant() {
            return typecarburant;
        }

        public String getCategorievehicule() {
            return categorievehicule;
        }

        public String getTypeboitevitesse() {
            return typeboitevitesse;
        }

        //seters


        public void setId(int id) {
            this.id = id;
        }


        public void setAnnee(Date annee) {
            this.annee = annee;
        }

        public void setKilometrage(double kilometrage) {
            this.kilometrage = kilometrage;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setLocalisation(String localisation) {
            this.localisation = localisation;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setMatricule(String matricule) {
            this.matricule = matricule;
        }

        public void setMarque(String marque) {
            this.marque = marque;
        }

        public void setModele(String modele) {
            this.modele = modele;
        }

        public void setTypecarburant(String typecarburant) {
            this.typecarburant = typecarburant;
        }

        public void setCategorievehicule(String categorievehicule) {
            this.categorievehicule = categorievehicule;
        }

        public void setTypeboitevitesse(String typeboitevitesse) {
            this.typeboitevitesse = typeboitevitesse;
        }

        //tostring


        @Override
        public String toString() {
            return
                    " annee=" + annee +
                    "\n kilometrage=" + kilometrage +
                    ",\n description='" + description + '\'' +
                    ", \n localisation='" + localisation + '\'' +
                    ", \n mail='" + mail + '\'' +
                    ", \n matricule='" + matricule + '\'' +
                    ", \n marque=" + marque +
                    ", \n modele=" + modele +
                    ", \n typecarburant=" + typecarburant +
                    ", \n categorievehicule=" + categorievehicule +
                    ", \n typeboitevitesse=" + typeboitevitesse
                    ;
        }
}
