import { Accordion, AccordionItem } from "@nextui-org/react";
import ClientTable from "./ClientTable";
import "../components/style/clientTable.css";
import { Pagination } from "@nextui-org/react";

export default function Login() {
  return (
    <div className="clients">
      <ClientTable></ClientTable>
      <Pagination color="secondary" initialPage={1} total={10} />
    </div>
  );
}
