package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SalableTest.class , HealthTest.class, ArmorTest.class, WeaponTest.class})
public class AllTests
{
	
}
