package emt.labs.lab1.repository.jpa;

import emt.labs.lab1.models.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccessoryInterface extends JpaRepository<Accessory,Long> {
}
