package com.luix.eldenbuilds.core.result;

public abstract class UiState<T> {

    private UiState() {}

    public static final class Loading<T> extends UiState<T> {}

    public static final class Empty<T> extends UiState<T> {}

    public static final class Success<T> extends UiState<T> {
        private final T data;
        public Success(T data) {
            this.data = data;
        }
        public T getData() {
            return data;
        }
    }

    public static final class Error<T> extends UiState<T> {
        private final Throwable error;
        public Error(Throwable error) {
            this.error = error;
        }
        public Throwable getError() {
            return error;
        }
    }

}
