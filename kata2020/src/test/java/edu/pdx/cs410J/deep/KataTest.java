package edu.pdx.cs410J.deep;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class KataTest
{

  @Test
  public void canInstantiateKataClass() {
    new Kata();
  }


  @Test
  public void Is1isI()
  {
     assertThat(Kata.romanNumeral(1),equalTo(null));
  }


}
