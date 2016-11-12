package es.iridiobis.stickycomponent.domain.model;


public class Person {
    private final String firstName;
    private final String lastName;

    private Person(final Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final Person copy) {
        Builder builder = new Builder();
        builder.firstName = copy.firstName;
        builder.lastName = copy.lastName;
        return builder;
    }


    /**
     * {@code Person} builder static inner class.
     */
    public static final class Builder {
        private String firstName;
        private String lastName;

        private Builder() {
        }

        /**
         * Sets the {@code firstName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param firstName the {@code firstName} to set
         * @return a reference to this Builder
         */
        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the {@code lastName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param lastName the {@code lastName} to set
         * @return a reference to this Builder
         */
        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Returns a {@code Person} built from the parameters previously set.
         *
         * @return a {@code Person} built with parameters of this {@code Person.Builder}
         */
        public Person build() {
            return new Person(this);
        }
    }
}
