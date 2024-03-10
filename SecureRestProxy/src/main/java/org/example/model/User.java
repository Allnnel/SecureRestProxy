package org.example.model;

import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email", nullable = false)
  private String email;

  @Embedded private Address address;

  @Column(name = "phone")
  private String phone;

  @Column(name = "website")
  private String website;

  @Embedded private Company company;

  public User() {}

  public User(
      String name,
      String username,
      String email,
      Address address,
      String phone,
      String website,
      Company company) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.company = company;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(name, user.name)
        && Objects.equals(username, user.username)
        && Objects.equals(email, user.email)
        && Objects.equals(address, user.address)
        && Objects.equals(phone, user.phone)
        && Objects.equals(website, user.website)
        && Objects.equals(company, user.company);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, username, email, address, phone, website, company);
  }

  @Getter
  @Setter
  @Embeddable
  static class Address {
    @Column(name = "street")
    private String street;

    @Column(name = "suite")
    private String suite;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private String zipcode;

    @Embedded private Geo geo;

    public Address() {}

    public Address(String street, String suite, String city, String zipcode) {
      this.street = street;
      this.suite = suite;
      this.city = city;
      this.zipcode = zipcode;
    }

    @Getter
    @Setter
    @Embeddable
    static class Geo {
      @Column(name = "lat")
      private String lat;

      @Column(name = "lng")
      private String lng;

      public Geo() {}

      public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
      }
    }
  }

  @Getter
  @Setter
  @Embeddable
  static class Company {
    @Column(name = "company_name")
    private String name;

    @Column(name = "catch_phrase")
    private String catchPhrase;

    @Column(name = "bs")
    private String bs;

    public Company() {}

    public Company(String name, String catchPhrase, String bs) {
      this.name = name;
      this.catchPhrase = catchPhrase;
      this.bs = bs;
    }
  }
}
