
public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV,double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(((this.xxPos - p.xxPos)*(this.xxPos-p.xxPos))+((this.yyPos-p.yyPos)*(this.yyPos-p.yyPos)));
    }

    public double calcForceExertedBy(Planet p){
        double distance = calcDistance(p);
        double gravity = 6.67e-11;
        return gravity * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double distanceX = p.xxPos - this.xxPos;
        return force * distanceX / distance;

    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double distanceX = p.yyPos - this.yyPos;
        return force * distanceX / distance;
    }

    public double calcNetForceExertedByX(Planet[] p){
        double sumForce = 0;
        for (int i = 0; i < p.length; i++) {
            if(this.equals(p[i])){
                continue;
            }
            sumForce += calcForceExertedByX(p[i]);
        }
        return sumForce;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double sumForce = 0;
        for (int i = 0; i < p.length; i++) {
            if(this.equals(p[i])){
                continue;
            }
            sumForce += calcForceExertedByY(p[i]);
        }
        return sumForce;
    }

    public void update(double dt, double fX, double fY){
        double netAccelerationX = fX / this.mass;
        double netAccelerationY = fY / this.mass;
        double newVelocityX = this.xxVel + dt * netAccelerationX;
        double newVelocityY = this.yyVel + dt * netAccelerationY;
        double positionNewX = this.xxPos + dt * newVelocityX;
        double positionNewY = this.yyPos + dt * newVelocityY;
        this.xxVel = newVelocityX;
        this.yyVel = newVelocityY;
        this.xxPos = positionNewX;
        this.yyPos = positionNewY;
    }

    public void draw(){
        String imgGif = "images/"+this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos,imgGif);
    }
}