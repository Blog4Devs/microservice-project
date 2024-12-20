import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
} from "@nextui-org/react";
import { Link as RLink } from "react-router-dom";
import "../components/style/navbar.css";
export const UserIcon = ({
  fill = "currentColor",
  size,
  height,
  width,
  ...props
}) => {
  return (
    <svg
      data-name="Iconly/Curved/Profile"
      height={size || height || 24}
      viewBox="0 0 24 24"
      width={size || width || 24}
      xmlns="http://www.w3.org/2000/svg"
      {...props}
    >
      <g
        fill="none"
        stroke={fill}
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeMiterlimit={10}
        strokeWidth={1.5}
      >
        <path
          d="M11.845 21.662C8.153 21.662 5 21.088 5 18.787s3.133-4.425 6.845-4.425c3.692 0 6.845 2.1 6.845 4.4s-3.134 2.9-6.845 2.9z"
          data-name="Stroke 1"
        />
        <path
          d="M11.837 11.174a4.372 4.372 0 10-.031 0z"
          data-name="Stroke 3"
        />
      </g>
    </svg>
  );
};
export default function Nav() {
  return (
    <Navbar className="navbar">
      <NavbarBrand>
        <svg fill="none" height="36" viewBox="0 0 32 32" width="36">
          <path
            clipRule="evenodd"
            d="M17.6482 10.1305L15.8785 7.02583L7.02979 22.5499H10.5278L17.6482 10.1305ZM19.8798 14.0457L18.11 17.1983L19.394 19.4511H16.8453L15.1056 22.5499H24.7272L19.8798 14.0457Z"
            fill="currentColor"
            fillRule="evenodd"
          />
        </svg>
        <p className="font-bold text-inherit">ACME</p>
      </NavbarBrand>
      <NavbarContent className="hidden sm:flex gap-20" justify="center">
        <NavbarItem>
          <Link color="secondary">
            <RLink to={"/ui/clients"}>Clients</RLink>
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link color="secondary" href="#">
            <RLink to={"/ui/vehicules"}>Vehicules</RLink>
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link color="secondary" href="#">
            <RLink to={"/ui/maintenances"}>Maintenances</RLink>
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link color="secondary" href="#">
            <RLink to={"/ui/invoice"}>Invoice</RLink>
          </Link>
        </NavbarItem>
      </NavbarContent>
      <NavbarContent justify="end">
        <NavbarItem>
          <Button className="button" href="#" variant="flat">
            <UserIcon></UserIcon>
            Add client
          </Button>
        </NavbarItem>
      </NavbarContent>
    </Navbar>
  );
}
