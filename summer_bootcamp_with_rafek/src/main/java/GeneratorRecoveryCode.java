import java.util.Random;

public class GeneratorRecoveryCode {

    /**
     * Generating and returning random number from range GenerateNumberRange.MAX - GenerateNumberRange.MIN
     * @return - random number
     */
    public String generateCode(){
        Random random = new Random();
        int randomNumber = random.nextInt(GenerateNumberRange.MAX.getNumber() + GenerateNumberRange.MIN.getNumber()) ;
        return String.valueOf(randomNumber);
    }
}
