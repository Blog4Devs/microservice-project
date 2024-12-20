import React from "react";

const Table = ({ data, columns, actions }) => {
  return (
    <div className="p-4 overflow-auto">
      <table className="min-w-full bg-white border text-black rounded-lg shadow-lg">
        <thead>
          <tr className="bg-blue-600 text-white">
            {columns.map((col) => (
              <th key={col.key} className="border px-6 py-4 text-left">
                {col.label}
              </th>
            ))}
            {/* Conditionally render the actions column if actions prop is passed */}
            {actions && <th className="border px-6 py-4 text-left">Actions</th>}
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((item, index) => (
              <tr key={index} className="hover:bg-blue-50">
                {columns.map((col) => (
                  <td key={col.key} className="border px-6 py-4">
                    {`${item[col.key]}`}
                  </td>
                ))}
                {/* Conditionally render the actions column if actions prop is passed */}
                {actions && (
                  <td className="border px-6 py-4">
                    <div className="flex flex-wrap gap-2">
                      {actions(item)} {/* Render actions dynamically */}
                    </div>
                  </td>
                )}
              </tr>
            ))
          ) : (
            <tr>
              <td
                colSpan={columns.length + (actions ? 1 : 0)}
                className="text-center py-4"
              >
                No clients found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
