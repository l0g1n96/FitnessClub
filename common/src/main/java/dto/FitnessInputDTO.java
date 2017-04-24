package dto;

public final class FitnessInputDTO {

    private final int optionNumber;

    public FitnessInputDTO(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FitnessInputDTO that = (FitnessInputDTO) o;

        return optionNumber == that.optionNumber;
    }

    @Override
    public int hashCode() {
        return optionNumber;
    }

    @Override
    public String toString() {
        return "FitnessInputDTO{" +
                "optionNumber=" + optionNumber +
                '}';
    }
}
