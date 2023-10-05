package org.example.exceptions;

public class ChildElementNotFoundException extends RuntimeException {


  public ChildElementNotFoundException(String baseElement, String childElement) {
    super(String.format("Element %s not found in the element %s", childElement, baseElement));
  }
}
